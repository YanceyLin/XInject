package com.lin.inject.core.core;

import android.app.Application;

import com.lin.inject.annotation.common.XInjectConstant;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/28
 *     desc   : 全局组标注类的执行者
 *     version: 1.0
 * </pre>
 */
public class XInjectApplicationExecutor extends XInjectExecutor {

    private static final String GROUP_NAME = XInjectConstant.LIN_INJECT_GROUP_DEFAULT;

    /**
     * 执行标注的标签类对应的方法
     *
     * @param application 应用对象
     */
    public void invokeXInjectApplicationExecute(Application application) {
        invokeGroupXInjectExecute(application,GROUP_NAME);
    }

}
