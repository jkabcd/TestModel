package yuncar.aten.com.Rainnum;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * project:UMPush
 * package:yuncar.aten.com.Rainnum
 * Created by 佘少雄 on 2018/7/4.
 * e-mail : 610184089@qq.com
 */

class NumberRainItem extends View{
    private  Paint paint;
    public NumberRainItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }
}


