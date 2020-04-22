package com.dmx.demo.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Matthew
 * Create Time: 2019-05-16 09:39
 */
public class ThreadPoolExecutorUtil {
    private volatile static ThreadPoolExecutor uploadThreadPoolExecutor = null;
    private volatile static ThreadPoolExecutor callBackThreadPoolExecutor = null;
    private ThreadPoolExecutorUtil(){

    }

    private static ThreadFactory uploadThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("upload-pool-%d").build();

    private static ThreadFactory callBackThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("upload-pool-%d").build();

    /**
     * 获取单实例线程池
     * @return
     */
    public static ThreadPoolExecutor getUploadThreadPoolExecutor(){
        if (uploadThreadPoolExecutor == null) {
            synchronized (ThreadPoolExecutorUtil.class) {
                if (uploadThreadPoolExecutor == null) {
                    uploadThreadPoolExecutor = new ThreadPoolExecutor(5, 10,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(100), uploadThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return uploadThreadPoolExecutor;
    }

    public static ThreadPoolExecutor getSingleThreadPoolExecutor(){
        if (callBackThreadPoolExecutor == null) {
            synchronized (ThreadPoolExecutorUtil.class) {
                if (callBackThreadPoolExecutor == null) {
                    callBackThreadPoolExecutor = new ThreadPoolExecutor(5, 10,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(100), callBackThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return callBackThreadPoolExecutor;
    }
}