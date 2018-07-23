package yuncar.aten.com.mymode.calendarView.listener;

import android.view.View;

import yuncar.aten.com.mymode.calendarView.bean.DateBean;

/**
 * 日期点击接口
 */
public interface OnSingleChooseListener {
    /**
     * @param view   id
     * @param date   time
     */
    void onSingleChoose(View view, DateBean date);
}
