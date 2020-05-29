package com.lin.inject.core.bridge;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/1/28
 *     desc   : 注解所属类需要实现的接口，以供反射调用
 *     version: 1.0
 * </pre>
 */
public interface IXInjectApp {

    /**
     * 实现调用的方法
     *
     * @param args 参数数组
     */
    void execute(Object... args);
}
