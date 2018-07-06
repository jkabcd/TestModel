package yuncar.aten.com.lay;

import android.support.v4.util.SparseArrayCompat;

/**
 * project:UMPush
 * package:yuncar.aten.com.lay
 * Created by 佘少雄 on 2018/6/15.
 * e-mail : 610184089@qq.com
 */

public class Pool<T> {
    private SparseArrayCompat<T> mPool;
    private New<T> mNewInstance;

    public Pool(New<T> newInstance) {
        mPool = new SparseArrayCompat<>();
        mNewInstance = newInstance;
    }

    public T get(int key) {
        T res = mPool.get(key);
        if (res == null) {
            res = mNewInstance.get();
            mPool.put(key, res);
        }
        return res;
    }

    public interface New<T> {
        T get();
    }
}
