package yuncar.aten.com.testmodel.utools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import yuncar.aten.com.mymode.banner.Banner;
import yuncar.aten.com.mymode.banner.BannerConfig;
import yuncar.aten.com.mymode.banner.listener.OnBannerListener;
import yuncar.aten.com.testmodel.R;

/**
 * project:TestModel
 * package:yuncar.aten.com.testmodel.utools
 * Created by 佘少雄 on 2018/7/25.
 * e-mail : 610184089@qq.com
 */

public class BarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,OnBannerListener {

    private Banner banner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_layout);
        banner = (Banner) findViewById(R.id.banner);
        banner.setImages(Util.showListImgUrl())
                .setBannerTitles(Util.showListTitle())
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(BarActivity.this)
                .start();
        banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                banner.updateBannerStyle(BannerConfig.NOT_INDICATOR);
                break;
            case 1:
                banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                break;
            case 2:
                banner.updateBannerStyle(BannerConfig.NUM_INDICATOR);
                break;
            case 3:
                banner.updateBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                break;
            case 4:
                banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                break;
            case 5:
                banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void OnBannerClick(int position) {

    }
}
