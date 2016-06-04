package etong.tuyang;

import etong.tuyang.picture.data.HttpHelper;

/**
 * Created by hwt on 2016/5/21.
 */

public class MyApplication extends android.app.Application {

    public final static boolean IS_DEV = true;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getInstance().init(this);
    }
}
