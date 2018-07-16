package yuncar.aten.com.buttonAnimal;

/**
 * project:UMPush
 * package:yuncar.aten.com.buttonAnimal
 * Created by 佘少雄 on 2018/7/16.
 * e-mail : 610184089@qq.com
 * 按钮动画
 */

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AnimationButton extends View {
    private int bg_color = 0xffbc7d53;
    int ra=0;
    PathMeasure mPathMeasure = new PathMeasure();
    public AnimationButton(Context context) {
        super(context);
        initPaint();
    }
    public AnimationButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    public AnimationButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw_oval_to_circle(canvas);
        if(mAnimatorValue!=0){
            mDst.reset();
            // 硬件加速的BUG
//            mDst.lineTo(0,0);
            float stop = mLength * mAnimatorValue;
            mPathMeasure.getSegment(0, stop, mDst, true);
            canvas.drawPath(mDst, mPaint);
        }
    }
    Paint  paint;
    Paint  mPaint;
    float mLength;
    Path mDst;
    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(bg_color);
        okpath();
        startAnimal();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPathMeasure.setPath(okpath, false);
        mLength = mPathMeasure.getLength();
        mDst = new Path();
    }
    int top=30;
    int left=30;
    int bottom=400;
    int right=800;
    private void draw_oval_to_circle(Canvas canvas) {

        RectF rect = new RectF();
        rect.top=top;
        rect.left=left;
        rect.bottom=bottom;
        rect.right=right;
        //画圆角矩形
        canvas.drawRoundRect(rect,ra,ra,paint);
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        float baseline = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        //矩形内居中
        canvas.drawText("国你qwerty国",rect.centerX(),baseline,paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawPath(okpath,paint);
        paint.setColor(bg_color);
        paint.setStyle(Paint.Style.FILL);
    }
    float  mAnimatorValue=0;
    public void startAnimal(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,175);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ra = (Integer) animation.getAnimatedValue();
                invalidate();
            }
        });
        ValueAnimator valueAnimator2 = ValueAnimator.ofInt(30,245);
        valueAnimator2.setDuration(3000);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int  ras = (Integer) animation.getAnimatedValue();
                left=ras;
                right=800-(ras-30);
                if(ras==245){
                    AnimationButton.this.setTranslationY(400);
                    final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                            invalidate();
                        }
                    });
                    valueAnimator.setDuration(2000);
                    valueAnimator.start();
                }else {
                    invalidate();
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator2).after(valueAnimator);
        animatorSet.start();
    }
    Path okpath;
    public void okpath(){
        okpath = new Path();
        okpath.moveTo(200,200);
        okpath.lineTo(250,300);
        okpath.lineTo(300,150);
    }

}

