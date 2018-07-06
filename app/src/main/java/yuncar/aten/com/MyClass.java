package yuncar.aten.com;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * project:UMPush
 * package:yuncar.aten.com
 * Created by 佘少雄 on 2018/7/6.
 * e-mail : 610184089@qq.com
 */

public class MyClass extends View {
    float x=100;
    float y=100;
    public MyClass(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyClass(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyClass(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void init(){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        Path path = new Path();
        path.moveTo(500,500);
        path.cubicTo(x-500,y-500,x,y,500,500);
        canvas.drawPath(path,paint);
        canvas.drawLine(500,500,x-500,y-500,paint);
        canvas.drawLine(x,y,x-500,y-500,paint);
        canvas.drawLine(500,500,x,y,paint);

    }
}
