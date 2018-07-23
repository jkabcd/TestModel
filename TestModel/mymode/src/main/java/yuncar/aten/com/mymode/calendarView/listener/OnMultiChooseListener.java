package yuncar.aten.com.mymode.calendarView.listener;

import android.view.View;

import yuncar.aten.com.mymode.calendarView.bean.DateBean;


/**
 * 多选接口
 */
public interface OnMultiChooseListener {
    /**
     * @param view  id
     * @param date   time
     * @param flag 多选时flag=true代表选中数据，flag=false代表取消选中
     */
    void onMultiChoose(View view, DateBean date, boolean flag);
}
