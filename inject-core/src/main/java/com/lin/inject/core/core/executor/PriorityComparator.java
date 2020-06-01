package com.lin.inject.core.core.executor;

import com.lin.inject.annotation.model.XInjectMessage;

import java.util.Comparator;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/29
 *     desc   : 标注信息记录类的比较器
 *     version: 1.0
 * </pre>
 */
public class PriorityComparator implements Comparator<XInjectMessage> {

    //优先级比较（值越大越优先）
    @Override
    public int compare(XInjectMessage m1, XInjectMessage m2) {
        return m2.getPriority() - m1.getPriority();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }


}
