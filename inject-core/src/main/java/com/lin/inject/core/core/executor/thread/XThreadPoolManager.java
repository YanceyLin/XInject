package com.lin.inject.core.core.executor.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *     author : 林熠贤
 *     e-mail : linyixianwork@163.com
 *     time   : 2020/5/29
 *     desc   : 线程池管理
 *     version: 1.0
 * </pre>
 */
public class XThreadPoolManager {

    private static XThreadPoolManager _Instance;

    public static XThreadPoolManager get() {
        if (_Instance == null) {
            _Instance = new XThreadPoolManager();
        }
        return _Instance;
    }

    private ExecutorService mIOExecutor;
    private ExecutorService mNetWorkExecutor;

    XThreadPoolManager() {
        //采取具有优先级的线程池管理器（等待队列具有优先级排序）
        mIOExecutor = new ThreadPoolExecutor(10, 30, 60, TimeUnit.SECONDS, new PriorityBlockingQueue<>());
        //可重用的线程池管理类
        mNetWorkExecutor = Executors.newFixedThreadPool(5);
    }

    public ExecutorService iOExecutor() {
        return mIOExecutor;
    }

    public ExecutorService netWorkExecutor() {
        return mNetWorkExecutor;
    }

}
