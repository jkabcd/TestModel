package yuncar.aten.com.umpush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aten.yuncar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.preview_checkbox)
    CheckBox previewCheckbox;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.re_ll2)
    RelativeLayout reLl2;
    @BindView(R.id.lll)
    LinearLayout lll;
    private int with;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        with = 0;
        ButterKnife.bind(this);
        previewCheckbox.post(new Runnable() {
            @Override
            public void run() {
                with =  previewCheckbox.getWidth();
            }
        });
        previewCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lll.setTranslationX(0);
            }
        });
    }
}
