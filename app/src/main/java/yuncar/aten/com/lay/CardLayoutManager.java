package yuncar.aten.com.lay;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * project:UMPush
 * package:yuncar.aten.com.lay
 * Created by 佘少雄 on 2018/6/15.
 * e-mail : 610184089@qq.com
 */

public class CardLayoutManager extends RecyclerView.LayoutManager {
    public static final int DEFAULT_GROUP_SIZE = 5;

    private int mGroupSize;
    private int mHorizontalOffset;
    private int mVerticalOffset;
    private int mTotalWidth;
    private int mTotalHeight;
    private int mGravityOffset;
    private boolean isGravityCenter;

    private Pool<Rect> mItemFrames;

    public CardLayoutManager(boolean center) {
        this(DEFAULT_GROUP_SIZE, center);
    }

    public CardLayoutManager(int groupSize, boolean center) {
        mGroupSize = groupSize;
        isGravityCenter = center;
        mItemFrames = new Pool<>(new Pool.New<Rect>() {
            @Override
            public Rect get() { return new Rect();}
        });
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) { return;}//当未布局--或--获取数量为空时
        detachAndScrapAttachedViews(recycler);//回收recycle到Scrap中（数据不解绑）
        View first = recycler.getViewForPosition(0);//获取第一条数据的视图
        measureChildWithMargins(first, 0, 0);//测量第一条数据的大小
        int itemWidth = getDecoratedMeasuredWidth(first);//获取第一数据的的宽度（包括外边距）
        int itemHeight = getDecoratedMeasuredHeight(first);//获取第一数据的的高度（包括外边距）

        int firstLineSize = mGroupSize / 2 + 1;//第一行显示的数据的个数（3,2）（9,5）
        int secondLineSize = firstLineSize + mGroupSize / 2;//第二行显示数据的个数（3,3）（9,9）
        if (isGravityCenter && firstLineSize * itemWidth < getHorizontalSpace()) {//居中并且每行数据的宽度小于整行的宽度
            mGravityOffset = (getHorizontalSpace() - firstLineSize * itemWidth) / 2;//设置偏移量居中显示
        } else {
            mGravityOffset = 0;//居左边显示
        }

        for (int i = 0; i < getItemCount(); i++) {
            Rect item = mItemFrames.get(i);//新建一个Rect
            float coefficient = isFirstGroup(i) ? 1.5f : 1.f;//判断是否在第一行中（true的话x轴偏移1.5倍,false的话偏移1倍）
            int offsetHeight = (int) ((i / mGroupSize) * itemHeight * coefficient);//设置偏移的高度

            // 每组的第一行
            if (isItemInFirstLine(i)) {//判断i是否是在每一组的第一行
                int offsetInLine = i < firstLineSize ? i : i % mGroupSize;
                item.set(mGravityOffset + offsetInLine * itemWidth, offsetHeight, mGravityOffset + offsetInLine * itemWidth + itemWidth, itemHeight + offsetHeight);
            }else {//在每组第二行
                int lineOffset = itemHeight / 2;
                int offsetInLine = (i < secondLineSize ? i : i % mGroupSize) - firstLineSize;
                item.set(mGravityOffset + offsetInLine * itemWidth + itemWidth / 2,
                        offsetHeight + lineOffset, mGravityOffset + offsetInLine * itemWidth + itemWidth  + itemWidth / 2,
                        itemHeight + offsetHeight + lineOffset);
            }
        }

        mTotalWidth = Math.max(firstLineSize * itemWidth, getHorizontalSpace());
        int totalHeight = getGroupSize() * itemHeight;
        if (!isItemInFirstLine(getItemCount() - 1)) { totalHeight += itemHeight / 2;}
        mTotalHeight = Math.max(totalHeight, getVerticalSpace());
        fill(recycler, state);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) { return;}
        Rect displayRect = new Rect(mHorizontalOffset, mVerticalOffset,
                getHorizontalSpace() + mHorizontalOffset,
                getVerticalSpace() + mVerticalOffset);

        // Rect rect = new Rect();
        // for (int i = 0; i < getChildCount(); i++) {
        // View item = getChildAt(i);
        // rect.left = getDecoratedLeft(item);
        // rect.top = getDecoratedTop(item);
        // rect.right = getDecoratedRight(item);
        // rect.bottom = getDecoratedBottom(item);
        // if (!Rect.intersects(displayRect, rect)) {
        // removeAndRecycleView(item, recycler);
        // }
        // }

        for (int i = 0; i < getItemCount(); i++) {
            Rect frame = mItemFrames.get(i);
            if (Rect.intersects(displayRect, frame)) {
                View scrap = recycler.getViewForPosition(i);
                addView(scrap);
                measureChildWithMargins(scrap, 0, 0);
                layoutDecorated(scrap, frame.left - mHorizontalOffset, frame.top - mVerticalOffset,
                        frame.right - mHorizontalOffset, frame.bottom - mVerticalOffset);
            }
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        if (mVerticalOffset + dy < 0) {
            dy = -mVerticalOffset;
        } else if (mVerticalOffset + dy > mTotalHeight - getVerticalSpace()) {
            dy = mTotalHeight - getVerticalSpace() - mVerticalOffset;
        }

        offsetChildrenVertical(-dy);
        fill(recycler, state);
        mVerticalOffset += dy;
        return dy;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        if (mHorizontalOffset + dx < 0) {
            dx = -mHorizontalOffset;
        } else if (mHorizontalOffset + dx > mTotalWidth - getHorizontalSpace()) {
            dx = mTotalWidth - getHorizontalSpace() - mHorizontalOffset;
        }

        offsetChildrenHorizontal(-dx);
        fill(recycler, state);
        mHorizontalOffset += dx;
        return dx;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    private boolean isItemInFirstLine(int index) {
        int firstLineSize = mGroupSize / 2 + 1;
        return index < firstLineSize || (index >= mGroupSize && index % mGroupSize < firstLineSize);
    }

    private int getGroupSize() {
        return (int) Math.ceil(getItemCount() / (float)mGroupSize);
    }

    private boolean isFirstGroup(int index) {
        return index < mGroupSize;
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }
}
