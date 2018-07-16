package yuncar.aten.com.paths;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;

import com.aten.yuncar.R;

/**
 * project:UMPush
 * package:yuncar.aten.com.paths
 * Created by 佘少雄 on 2018/7/6.
 * e-mail : 610184089@qq.com
 * 学习贝赛尔曲线
 */

public class MainActivity6 extends AppCompatActivity {

    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        imageButton = (ImageButton) findViewById(R.id.imagebtn);
    }

    public void onImgClick(View view) {
       WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int gg=(displayMetrics.widthPixels-imageButton.getWidth())/2;
        ViewPath path = new ViewPath(); //偏移坐标

//        path.moveTo(0,0);
//        path.lineTo(displayMetrics.widthPixels-imageButton.getWidth(),0);
//        path.quadTo((displayMetrics.widthPixels-imageButton.getWidth())/2,50,displayMetrics.widthPixels-imageButton.getWidth(),500);
//        path.curveTo(300,1100,600,1800,0,500);
//        path.lineTo(0,0);
        path.moveTo(gg, 0);
//        path.lineTo(displayMetrics.widthPixels / 5 - displayMetrics.widthPixels / 2, 0);
        path.curveTo( gg+400 , 600 ,gg-400  , 1200 , gg, 0);

        ObjectAnimator anim = ObjectAnimator.ofObject(this,"fabLoc",new ViewPathEvaluator(),path.getPoints().toArray());
        anim.setInterpolator(new AccelerateInterpolator());
        anim.setDuration(3000);
//        anim.start();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 1000);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               float value= (float) animation.getAnimatedValue();
                if(value>=0&&value<500){
                    imageButton.setScaleX(value/500+1);
                    imageButton.setScaleY(value/500+1);
                }else {
                    imageButton.setScaleX((1000-value)/500+1);
                    imageButton.setScaleY((1000-value)/500+1);
                }
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(anim,valueAnimator);
        set.start();
    }


    public void setFabLoc(ViewPoint newLoc){
        imageButton.setTranslationX(newLoc.x);
        imageButton.setTranslationY(newLoc.y);
    }
}
