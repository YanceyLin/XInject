package com.lin.inject.core.core.executor.thread;

import com.lin.inject.annotation.model.XInjectMessage;

import java.util.Comparator;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/6/1
 *     desc   : 自定义带有优先级的线程类
 *     version: 1.0
 * </pre>
 */
public abstract class XPriorityRunnable implements Runnable, Comparator<XInjectMessage> {

    XInjectMessage mXInjectMessage;

    public XPriorityRunnable(XInjectMessage xInjectMessage) {
        this.mXInjectMessage = xInjectMessage;
    }

    @Override
    public void run() {
        if (mXInjectMessage!=null){
            dealXInject(mXInjectMessage);
        }
    }

    protected abstract void dealXInject(XInjectMessage xInjectMessage);


    //优先级比较（值越大越优先）
    @Override
    public int compare(XInjectMessage m1, XInjectMessage m2) {
        return m2.getPriority() - m1.getPriority();
    }

    public XInjectMessage getXInjectMessage() {
        return mXInjectMessage;
    }
}
