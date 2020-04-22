package com.dmx.demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Matthew
 * Create Time: 2019-08-28 23:30
 */
@Service
public class ExecutorCompletionServiceDemo {
    private static ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getUploadThreadPoolExecutor();
    private static ThreadPoolExecutor singleThreadPoolExecutor = ThreadPoolExecutorUtil.getSingleThreadPoolExecutor();

    private static AtomicInteger rejectCount = new AtomicInteger();
    private static AtomicInteger callBackRejectCount = new AtomicInteger();
    private static AtomicInteger timeOutCount = new AtomicInteger();
    private static AtomicInteger interruptedCount = new AtomicInteger();
    private static AtomicInteger finishedCount = new AtomicInteger();

    public void fun(final int j) {

        Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws InterruptedException {
                    //do something
                    System.out.println("task: "+j);
                    Thread.sleep(5000);
                    return j % 2 == 0;
                }
            };

        ExecutorCompletionService<Boolean> completionService = new ExecutorCompletionService<>(executor);
        Future<Boolean> submit = null;
        try {
            submit = completionService.submit(callable);
        } catch (RejectedExecutionException e) {
            rejectCount.addAndGet(1);
            System.out.println("RejectedExecutionException:"+executor);

        }

        final Future<Boolean> finalSubmit = submit;
        try {
            singleThreadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        assert finalSubmit != null;
                        Boolean aBoolean = finalSubmit.get(10, TimeUnit.SECONDS);
                        System.out.println("task:"+ aBoolean);
                    } catch (ExecutionException | InterruptedException e) {
                        System.out.println("ExecutionException | InterruptedException");
                        interruptedCount.addAndGet(1);
                    } catch (TimeoutException e) {
                        timeOutCount.addAndGet(1);
                        System.out.println("TimeoutException");
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            callBackRejectCount.addAndGet(1);
            System.out.println("被回调处理线程池拒绝");
        }
//        executor.shutdown();
//        singleThreadPoolExecutor.shutdown();
        long completedTaskCount = executor.getCompletedTaskCount();
        System.out.println("completedTaskCount:" + completedTaskCount);
        System.out.println("executor:"+executor);
        System.out.println("singleThreadPoolExecutor:"+singleThreadPoolExecutor);
        finishedCount.addAndGet(1);

        System.out.println(String.format("finishedCount:%s; timeOutCount:%s; rejectCount:%s; interruptedCount:%s； callBackRejectCount:%s",
                finishedCount.get(), timeOutCount.get(), rejectCount.get(), interruptedCount.get(), callBackRejectCount.get()));
    }

    public static void main(String[] args) {
    }
}