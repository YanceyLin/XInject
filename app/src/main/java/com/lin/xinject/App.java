package com.lin.xinject;

import android.app.Application;
import android.util.Log;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("onCreate", "Application");
    }
}
