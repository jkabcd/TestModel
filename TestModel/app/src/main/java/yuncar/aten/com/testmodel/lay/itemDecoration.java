package yuncar.aten.com.testmodel.lay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import yuncar.aten.com.testmodel.R;

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
    private int tope;

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
            if(isfirst(position)){
                drawTitleArea(c,parent.getLeft(),parent.getRight(),view,params,position);
            }
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

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
       int view_visiable_position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
       View vvvv = linearLayoutManager.getChildAt(1);
        if(vvvv!=null){
           TextView vvvv2= (TextView) vvvv.findViewById(R.id.text);
            Log.e("ss","daaoer：..."+vvvv2.getText());
        }
        if(null!=linearLayoutManager.getChildAt(view_visiable_position)){
            TextView textView = (TextView) linearLayoutManager.getChildAt(view_visiable_position).findViewById(R.id.text);
            Log.e("ss","数据：..."+textView.getText());
        }
//        if(parent.getChildAt(view_visiable_position+1)!=null){
//            Log.e("ccc","位置"+(view_visiable_position+1)+"数据"+parent.getChildAt(view_visiable_position+1).getTop());
//        }
       if(isfirst(view_visiable_position+1)){//判断可见的第二个数据是否不在同一组
           if(parent.getChildAt(view_visiable_position)!=null){
               tope = Math.max(divheight,parent.getChildAt(view_visiable_position).getTop());
           }
//           c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + divheight, paint);
//           c.drawText("asdfasdjfla", parent.getPaddingLeft()+100, parent.getPaddingTop() + divheight - (divheight / 2 - mBounds.height() / 2), mTextPaint);
       }else {//判断可见的第二个数据是在同一组
           c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + divheight, paint);
           c.drawText("asdfasdjfla", parent.getPaddingLeft()+100, parent.getPaddingTop() + divheight - (divheight / 2 - mBounds.height() / 2), mTextPaint);
       }


        Log.e("位置",linearLayoutManager.findFirstCompletelyVisibleItemPosition()+"寻求位置parent.getPaddingTop()");
//        for (int i = 0; i < childcount; i++) {
//            View view = parent.getChildAt(i);
//            final int bottom =   view.getTop();
//            int position =  parent.getChildAdapterPosition(view);
//            if(isfirst(position)){
//                int tope =  Math.max(divheight,bottom);
//                Paint.FontMetrics fm = mTextPaint.getFontMetrics();
//                float baseLine = tope - (100 - (fm.bottom - fm.top)) / 2 - fm.bottom;
//                c.drawRect(parent.getPaddingLeft(), tope, parent.getRight() - parent.getPaddingRight(), tope + divheight, paint);
//                c.drawText("asdfasdjfla", parent.getPaddingLeft()+100, baseLine, mTextPaint);
//            }else {
//                c.drawRect(left,bottom-2,right,bottom,paint);
//            }
//        }
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
        mTextPaint.getTextBounds("lkjasdlkjl", 0, "lkjasdlkjl".length(), mBounds);
        c.drawText("lkjasdlkjl", child.getPaddingLeft()+200, child.getTop() - params.topMargin - (divheight / 2 - mBounds.height() / 2), mTextPaint);
    }

}
