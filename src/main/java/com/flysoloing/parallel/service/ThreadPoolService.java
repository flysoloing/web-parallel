package com.flysoloing.parallel.service;

import com.flysoloing.parallel.domain.BusinessBean;
import com.flysoloing.parallel.domain.Result;
import com.flysoloing.parallel.domain.ThreadPoolTask;
import com.flysoloing.parallel.rpc.ThreadPoolRpc;
import com.jd.payment.paycommon.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <br>
 * User: laitao<br>
 * Date: 14-10-11<br>
 * Time: 上午10:26<br>
 */
public class ThreadPoolService {

    private ThreadPoolRpc threadPoolRpc;

    public ThreadPoolService() {
        this.threadPoolRpc = new ThreadPoolRpc();
    }

    public ThreadPoolService(ThreadPoolRpc threadPoolRpc) {
        this.threadPoolRpc = threadPoolRpc;
    }

    public Result threadPoolService(List<String> taskList, BusinessBean bean) {
        Result result = new Result();

        CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
        List<Future<Result>> futureList = new ArrayList<>(taskList.size());

        try {
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

            for (String taskName : taskList) {
                ThreadPoolTask task = new ThreadPoolTask(threadPoolRpc, bean);
                Future<Result> future;
                future = executor.submit(task);
                futureList.add(future);
                countDownLatch.countDown();
            }

            countDownLatch.await();

            for (Future<Result> future : futureList) {
                Result temp = new Result();
                temp = future.get();
                result.put(String.valueOf(future.hashCode()), GsonUtils.toJson(future));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }
}
