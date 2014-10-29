package com.flysoloing.parallel.threadpool;

import com.flysoloing.parallel.common.BusinessBean;
import com.flysoloing.parallel.common.Result;

import java.util.concurrent.Callable;

/**
 * <br>
 * User: laitao<br>
 * Date: 14-10-11<br>
 * Time: 上午10:32<br>
 */
public class ThreadPoolTask implements Callable<Result> {

    private ThreadPoolRpc threadPoolRpc;

    private BusinessBean bean;

    public ThreadPoolTask(ThreadPoolRpc threadPoolRpc, BusinessBean bean) {
        this.threadPoolRpc = threadPoolRpc;
        this.bean = bean;
    }

    @Override
    public Result call() throws Exception {
        return threadPoolRpc.cardBinVerify(bean);
    }
}
