package yuncar.aten.com.testmodel.lay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

/**
 * project:TestModel
 * package:yuncar.aten.com.testmodel.lay
 * Created by 佘少雄 on 2018/7/24.
 * e-mail : 610184089@qq.com
 */

public class itemDecoration extends RecyclerView.ItemDecoration {
    int divheight=60;
    private TextPaint mTextPaint;
    private Rect mBounds;
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Log.e("onDraw","onDraw");
//        setDivheight(c,parent);
        int childcount =   parent.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View view = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            int position = params.getViewLayoutPosition();
            drawTitleArea(c,parent.getLeft(),parent.getRight(),view,params,position);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        mBounds = new Rect();
        Log.e("getItemOffsets","getItemOffsets");
        if(isfirst(parent.getChildAdapterPosition(view))){
            outRect.top=divheight;
        }else {
            outRect.top=0;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.e("onDrawOver","onDrawOver");
        setDivheight(c,parent);
    }
    public void setDivheight(Canvas c, RecyclerView parent){
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setColor(Color.RED);
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mTextPaint.getTextBounds("abchhhh", 0, "abchhhh".length(), mBounds);

        final  int left = parent.getLeft();
        final  int right = parent.getRight();
        int childcount =   parent.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View view = parent.getChildAt(i);
            final int bottom =   view.getTop();
            int position =  parent.getChildAdapterPosition(view);
            if(isfirst(position)){
                int tope =  Math.max(divheight,bottom+parent.getPaddingTop());
//             if(position+1<=state.getItemCount()){
                Paint.FontMetrics fm = mTextPaint.getFontMetrics();
                float baseLine = tope - (100 - (fm.bottom - fm.top)) / 2 - fm.bottom;
                c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + divheight, paint);
//                c.drawRect(left,baseLine-25,right,baseLine+25,paint);
//                c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + divheight, paint);
//                c.drawText("asdfasdjfla", left, baseLine, mTextPaint);
                c.drawText("asdfasdjfla", view.getPaddingLeft()+100,
                        parent.getPaddingTop() + divheight - (divheight / 2 - mBounds.height() / 2),
                        mTextPaint);
//        c.drawText("asdfasdjfla", 0,
//                parent.getPaddingTop() + divheight - (divheight / 2 - view.getHeight()/ 2),
//                mTextPaint);
//             }
            }else {
                c.drawRect(left,bottom-2,right,bottom,paint);
            }
        }
    }
    public boolean isfirst(int posi){
        if(posi%3==0){
            return true;
        }
        return false;
    }
    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {//最先调用，绘制在最下层

        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setColor(Color.RED);
        c.drawRect(left, child.getTop() - params.topMargin - divheight, right, child.getTop() - params.topMargin, paint);
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
/*
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;*/
        mTextPaint.getTextBounds("lkjasdlkjl", 0, "lkjasdlkjl".length(), mBounds);
        c.drawText("lkjasdlkjl", child.getPaddingLeft(), child.getTop() - params.topMargin - (divheight / 2 - mBounds.height() / 2), mTextPaint);
    }

}
