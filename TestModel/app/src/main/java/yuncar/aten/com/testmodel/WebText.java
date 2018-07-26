package yuncar.aten.com.testmodel;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import yuncar.aten.com.mymode.tools.PermissionUtils;

/**
 * project:TestModel
 * package:yuncar.aten.com.testmodel
 * Created by 佘少雄 on 2018/7/25.
 * e-mail : 610184089@qq.com
 */

public class WebText extends AppCompatActivity{
    private final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
            , Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CALENDAR,Manifest.permission.CAMERA};
    private final int REQUEST_CODE_CAMERA = 1,REQUEST_CODE_PERMISSIONS=2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_re);

        requestMorePermissions();
        Glide.with(WebText.this).load(new File("")).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

            }
        });

        NoScrollWebView progressBarWebView = (NoScrollWebView) findViewById(R.id.pro);
//        progressBarWebView.callHandler("", "", new JavaCallHandler() {
//            @Override
//            public void OnHandler(String handlerName, String jsResponseData) {
//
//            }
//        });
     progressBarWebView.loadUrl("http://a1.7x24cc.com/phone_webChat.html?accountId=N000000006117&chatId=29cd3c81-3d3a-46b1-bda0-ae70f0c82d0c");
    }

    // 普通申请多个权限
    private void requestMorePermissions(){
        PermissionUtils.checkAndRequestMorePermissions(WebText.this, PERMISSIONS, REQUEST_CODE_PERMISSIONS,
                new PermissionUtils.PermissionRequestSuccessCallBack() {
                    @Override
                    public void onHasPermission() {
                        // 权限已被授予
//                        toCamera();
                    }
                });
    }
}
