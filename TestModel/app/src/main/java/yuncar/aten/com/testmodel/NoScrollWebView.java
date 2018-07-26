package yuncar.aten.com.testmodel;

import android.content.Context;
import android.util.AttributeSet;

import im.delight.android.webview.AdvancedWebView;

/**
 * project:TestModel
 * package:yuncar.aten.com.testmodel
 * Created by 佘少雄 on 2018/7/25.
 * e-mail : 610184089@qq.com
 */

public class NoScrollWebView extends AdvancedWebView {

    public NoScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NoScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollWebView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
