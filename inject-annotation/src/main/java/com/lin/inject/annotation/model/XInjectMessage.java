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

    private Map<String, String> map;

    public XInjectMessage(String className, XInject xInject) {
        this.className = className;
        map = new HashMap<>();
        map.put("id", xInject.id());
        map.put("group", xInject.group());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
