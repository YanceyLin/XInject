package com.lin.test.module.a;

import android.app.Application;
import android.util.Log;

import com.lin.inject.annotation.XInject;
import com.lin.inject.core.bridge.XInjectAppCore;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@XInject(name = "a", group = "app", priority = 2, inMainThread = false)
public class A extends XInjectAppCore {

    void add() {

    }

    @Override
    protected void onCreate(Application application) {
        Log.d("XInjectAppCore", "A 类调用了，日志输出一下");
    }
}
