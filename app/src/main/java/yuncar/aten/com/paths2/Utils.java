package yuncar.aten.com.paths2;

import android.content.Context;

/**
 * project:UMPush
 * package:yuncar.aten.com.paths2
 * Created by 佘少雄 on 2018/7/6.
 * e-mail : 610184089@qq.com
 */

public class Utils {
    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
