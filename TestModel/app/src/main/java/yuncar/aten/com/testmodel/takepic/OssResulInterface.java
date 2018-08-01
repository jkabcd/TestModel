package yuncar.aten.com.testmodel.takepic;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

/**
 * project:TestModel
 * package:yuncar.aten.com.testmodel.takepic
 * Created by 佘少雄 on 2018/7/27.
 * e-mail : 610184089@qq.com
 */

public interface OssResulInterface {
    public void resultSuccess(PutObjectRequest success);
    public void resultFaile(ClientException clientExcepion, ServiceException serviceException);
    public void uploading(long data);
}
