package yuncar.aten.com.mymode.pullzoom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * project:yfqc_android
 * package:com.aten.yuncar.widget.pullzoom
 * Created by ATEN-user-056 on 2018/5/16.
 * e-mail : 610184089@qq.com
 */

public class ObservableScrollView extends ScrollView {


    private ScrollViewListener mScrollViewListener=null;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}

