package yuncar.aten.com.mymode.calendarView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yuncar.aten.com.mymode.R;
import yuncar.aten.com.mymode.calendarView.listener.OnPagerChangeListener;
import yuncar.aten.com.mymode.calendarView.weiget.CalendarView;

/**
 * project:yfqc_android日历弹窗
 * package:com.aten.yuncar.widget.calendarView
 * Created by ATEN-user-056 on 2018/5/10.
 * e-mail : 610184089@qq.com
 */

public class CalendarViewDialog extends Dialog {
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;

    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private TextView tv_datemonth;
    private ImageView imageView_left;
    private ImageView imageView_right;
    private CalendarView calendarView;
    private TextView tv_countday;

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str 位置
     * @param onNoOnclickListener 位置
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str 位置
     * @param onYesOnclickListener 位置
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }
    List<Long> longList;
    int data;
    public CalendarViewDialog(Context context,List<Long> longList,int data) {
        super(context, R.style.Dialog);
        this.longList=longList;
        this.data=data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendar);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);

        //初始化界面控件
        initView();
        //初始化界面数据
//        initData();
        //初始化界面控件的事件
//        initEvent();
        tv_countday = (TextView) findViewById(R.id.tv_countday);
        tv_countday.setText("已连续签到"+data+"天");
        imageView_left = (ImageView) findViewById(R.id.img_left);
        imageView_right = (ImageView) findViewById(R.id.img_right);
        imageView_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.lastMonth();
            }
        });
        imageView_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.nextMonth();
            }
        });
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }



    /**
     * 初始化界面控件
     */
    private void initView() {
        tv_datemonth = (TextView) findViewById(R.id.tv_datemonth);
        List<String> list = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
       String da= simpleDateFormat.format(new Date());
        tv_datemonth.setText(da.substring(0,4)+"年"+da.substring(5,7)+"月");
        if(longList.size()>0){
            for (int i = 0; i < longList.size(); i++) {
                list.add( simpleDateFormat.format(new Date(longList.get(i))));
            }
        }
        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView
                .setStartEndDate("2017.1", "2019.12")
                .setDisableStartEndDate("2017.10.7", "2019.10.7")
                .setInitDate(da)
                .setMultiDate(list)
                .init();
        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                tv_datemonth.setText(date[0]+"年"+date[1]+"月");
            }
        });



     /*   HashMap<String, String> map = new HashMap<>();
        map.put("2017.10.30", "qaz");
        map.put("2017.10.1", "wsx");
        map.put("2017.11.12", "yhn");
        map.put("2017.9.15", "edc");
        map.put("2017.11.6", "rfv");
        map.put("2017.11.11", "tgb");
//日历init，年月日之间用点号隔开
        calendarView
                .setStartEndDate("2017.7", "2018.12")
                .setSpecifyMap(map)
                .setInitDate("2017.11")
                .setSingleDate("2017.12.12")
                .init();*/

//月份切换回调
//        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
//            @Override
//            public void onPagerChanged(int[] date) {
//
//            }
//        });

//单选回调
//        calendarView.setOnItemClickListener(new OnMonthItemClickListener() {
//            @Override
//            public void onMonthItemClick(View view, DateBean date) {
//
//            }
//        });
//        yes = (Button) findViewById(R.id.yes);
//        no = (Button) findViewById(R.id.no);
//        titleTv = (TextView) findViewById(R.id.title);
//        messageTv = (TextView) findViewById(R.id.message);
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title 位置
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
