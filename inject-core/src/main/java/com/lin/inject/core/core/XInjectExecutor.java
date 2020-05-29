package com.lin.inject.core.core;

import android.app.Application;

import com.lin.inject.annotation.model.XInjectMessage;
import com.lin.inject.core.bridge.IXInjectApp;
import com.lin.inject.core.util.XInjectUtils;

import java.util.List;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/29
 *     desc   : 标注类的执行者
 *     version: 1.0
 * </pre>
 */
public class XInjectExecutor {

    public static XInjectExecutor init() {
        return new XInjectExecutor();
    }

    /**
     * 执行所有标注的标签类对应的方法
     *
     * @param application 应用对象
     */
    public void invokeAllXInjectExecute(Application application) {
        for (String group : XInjectUtils.getClassInfoCacheMap().keySet()) {
            invokeGroupXInjectExecute(application, group);
        }
    }

    /**
     * 执行指定组别的标签类对应方法
     *
     * @param application 应用对象
     * @param group       组别键值
     */
    public void invokeGroupXInjectExecute(Application application, String group) {
        List<XInjectMessage> list = XInjectUtils.getClassInfoCacheMap().get(group);
        if (list != null && list.size() > 0) {
            for (XInjectMessage xInjectMessage : list) {
                invokeXInjectExecute(application, xInjectMessage);
            }
        }
    }

    /**
     * 执行指定的XInject标注类的方法
     *
     * @param application    应用实例
     * @param xInjectMessage 标注类对应的标签信息
     */
    private void invokeXInjectExecute(Application application, XInjectMessage xInjectMessage) {
        try {
            String className = xInjectMessage.getClassName();
            Class mClass = Class.forName(className);
            IXInjectApp iXInjectApp = (IXInjectApp) mClass.newInstance();
            iXInjectApp.execute(application);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
