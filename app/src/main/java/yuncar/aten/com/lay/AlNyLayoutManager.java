package yuncar.aten.com.lay;

import android.graphics.Rect;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * project:UMPush
 * package:yuncar.aten.com.lay
 * Created by 佘少雄 on 2018/7/10.
 * e-mail : 610184089@qq.com
 */

public class AlNyLayoutManager extends RecyclerView.LayoutManager{
    private SparseArrayCompat<Rect> itemFrames = new SparseArrayCompat<>();
    private int totalHeight;
    private int verticalOffset;
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return  new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if(getItemCount()<=0){
            return;
        }
        int offsetY = 0;
        detachAndScrapAttachedViews(recycler);
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int height = getDecoratedMeasuredHeight(view);
            layoutDecorated(view,0,totalHeight,300,totalHeight+height);
            Rect rect = itemFrames.get(i);
            if (rect == null) {
                rect = new Rect();
            }

            rect.set(0, offsetY, 300, offsetY + height);
            itemFrames.put(i, rect);
            //横竖方向的偏移
            offsetY += height;

            totalHeight += height;
        }
//        super.onLayoutChildren(recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }
    int dys=0;
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler) ;
        if(verticalOffset+dy<0){//滑到顶部
            dys = -verticalOffset;
        }else if(verticalOffset+dy>totalHeight-(getHeight() - getPaddingBottom() - getPaddingTop())){//滑到底部
           dys = totalHeight - (getHeight() - getPaddingBottom() - getPaddingTop()) - verticalOffset;
        }else {
            dys=dy;
        }
        offsetChildrenVertical(-dys);
        fill(recycler);
        verticalOffset += dys;

        return dys;
    }
    //回收不必要的view（超出屏幕的），取出需要的显示出来
    private void fill(RecyclerView.Recycler recycler) {
        //获得屏幕的边界信息
        Rect displayFrame = new Rect(0, verticalOffset,  getWidth() - getPaddingLeft() - getPaddingRight(),
                verticalOffset + getHeight() - getPaddingBottom() - getPaddingTop());

        //滑出屏幕回收到缓存中
        Rect childFrame = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            childFrame.left = getDecoratedLeft(view);
            childFrame.top = getDecoratedTop(view);
            childFrame.right = getDecoratedRight(view);
            childFrame.bottom = getDecoratedBottom(view);
            //判断是否在显示区域里面
            if (!Rect.intersects(displayFrame, childFrame)) {
                removeAndRecycleView(view, recycler);
            }
        }

        //在屏幕上显示出
        for (int i = 0; i < getItemCount(); i++) {
            if (Rect.intersects(displayFrame, itemFrames.get(i))) {//判断是否在屏幕中
                View view = recycler.getViewForPosition(i);
                measureChildWithMargins(view, 0, 0);
                addView(view);
                Rect rect = itemFrames.get(i);
                layoutDecorated(view, rect.left - 0, rect.top - verticalOffset,
                        rect.right - 0, rect.bottom - verticalOffset);//将ViewLayout出来，显示在屏幕上，内部会自动追加上该View的ItemDecoration和Margin。此时我们的View已经可见了
            }
        }


    }
}
