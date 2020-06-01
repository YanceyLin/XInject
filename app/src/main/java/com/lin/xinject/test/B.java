package com.lin.xinject.test;

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
@XInject(name = "b",group = "app",priority = 1,inMainThread = false)
public class B extends XInjectAppCore {

    void bbb(){

    }

    @Override
    protected void onCreate(Application application) {
        Log.d("XInjectAppCore","B 类调用了，======");
    }
}
