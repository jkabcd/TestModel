package yuncar.aten.com.umpush;

import android.app.Application;

/**
 * project:UMPush
 * package:yuncar.aten.com.umpush
 * Created by ATEN-user-056 on 2018/5/10.
 * e-mail : 610184089@qq.com
 */

public class Appliac extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "8ac1f192ada23be9b5f855ac9e67273b");
//        UMConfigure.setLogEnabled(true);
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                Log.e("ssss","asd成功"+deviceToken);
//                //注册成功会返回device token
//            }
//            @Override
//            public void onFailure(String s, String s1) {
//                Log.e("ssss","asdf失败"+s+"```````````````"+s1);
//            }
//        });
    }
}
