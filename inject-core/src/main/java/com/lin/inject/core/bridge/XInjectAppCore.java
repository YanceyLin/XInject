package com.lin.inject.core.bridge;

import android.app.Application;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/28
 *     desc   : 使用XInject所需要实现的基类，便于后续反射调用
 *     version: 1.0
 * </pre>
 */
public abstract class XInjectAppCore implements IXInjectApp {

    @Override
    public void execute(Object... args) {
        if (args != null && args.length > 0) {
            for (Object obj : args) {
                if (obj instanceof Application) {
                    onCreate((Application) obj);
                    break;
                }
            }
        }
    }

    protected abstract void onCreate(Application application);

}
