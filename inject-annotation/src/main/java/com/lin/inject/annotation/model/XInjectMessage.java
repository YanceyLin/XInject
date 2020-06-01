package com.lin.inject.annotation.model;

import com.lin.inject.annotation.XInject;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/28
 *     desc   : 对于携带注解的类信息收集器
 *     version: 1.0
 * </pre>
 */
public class XInjectMessage {

    private String className;

    private String name;
    private String group;
    private int priority;
    private boolean inMainThread;

    public XInjectMessage(String className, XInject xInject) {
        this.className = className;
        name = xInject.name();
        group = xInject.group();
        priority = xInject.priority();
        inMainThread = xInject.inMainThread();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isInMainThread() {
        return inMainThread;
    }

    public void setInMainThread(boolean inMainThread) {
        this.inMainThread = inMainThread;
    }
}
