package yuncar.aten.com.mymode.calendarView.listener;

import android.view.View;
import android.widget.TextView;

import yuncar.aten.com.mymode.calendarView.bean.DateBean;


public interface CalendarViewAdapter {
    /**
     * 返回阳历、阴历两个TextView
     *
     * @param view
     * @param date
     * @return datas
     */
    TextView[] convertView(View view, DateBean date);
}
