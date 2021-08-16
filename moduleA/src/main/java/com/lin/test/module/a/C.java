package com.lin.test.module.a;

import android.app.Application;
import android.util.Log;

import com.lin.inject.annotation.XInject;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@XInject(name = "c", group = "app",priority = 3,inMainThread = false)
public class C extends A {

    void ccc() {

    }

    @Override
    protected void onCreate(Application application) {
        Log.d("XInjectAppCore", "*********** C类调用了，======");
    }
}
