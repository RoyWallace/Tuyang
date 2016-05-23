package etong.tuyang.util;

import android.app.Activity;

/**
 * Created by etong on 16-5-23.
 */
public class UIUtil {

    public static int getScreenWidth(Activity activity){
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }
}
