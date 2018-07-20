package yuncar.aten.com.testmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.allen.library.SuperTextView;

/**
 * project:TestModel
 * package:yuncar.aten.com.testmodel
 * Created by 佘少雄 on 2018/7/19.
 * e-mail : 610184089@qq.com
 */

public class TestTranceActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private TextView tv_1;
    private int width;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_text);
        checkBox = (CheckBox) findViewById(R.id.ck_1);
        tv_1 = (TextView) findViewById(R.id.tv_1);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        width = dm.widthPixels;
        // 屏幕高度（像素）
        height = dm.heightPixels;
        tv_1.getLayoutParams().width=width;
        SuperTextView tes = (SuperTextView) findViewById(R.id.tes);
        tes.setCbChecked(true);
    }
    public void aaa(View v){
        tv_1.setTranslationX(-checkBox.getWidth());
    }
    public void bbb(View v){
        tv_1.setTranslationX(0);
//        checkBox.setVisibility(View.VISIBLE);
    }
}
