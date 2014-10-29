package com.flysoloing.parallel.forkjoin;

import com.flysoloing.parallel.common.BusinessBean;
import com.flysoloing.parallel.common.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * <br>
 * User: laitao<br>
 * Date: 14-10-9<br>
 * Time: 下午2:04<br>
 */
public class ForkJoinTask extends RecursiveTask<Result> {

    public static final int THRESHOLD = 7;

    private ForkJoinRpc forkJoinRpc;

    private BusinessBean bean;

    private List<String> taskList = new ArrayList<String>();

    public ForkJoinTask() {
        super();
    }

    public ForkJoinTask(ForkJoinRpc forkJoinRpc, BusinessBean bean) {
        this.forkJoinRpc = forkJoinRpc;
        this.bean = bean;
    }

    public ForkJoinTask(List<String> taskList, ForkJoinRpc forkJoinRpc, BusinessBean bean) {
        this(forkJoinRpc, bean);
        this.taskList = taskList;
    }

    @Override
    protected Result compute() {
        Result result = new Result();
//        if (taskList.size() > THRESHOLD) {
//            System.out.println("do fork, current size is " + taskList.size());
//            List<String> subTaskList1 = taskList.subList(0, taskList.size()/2);
//            List<String> subTaskList2 = taskList.subList(taskList.size()/2, taskList.size());
//            new ForkJoinTask(subTaskList1, forkJoinRpc, bean).fork();
//            new ForkJoinTask(subTaskList2, forkJoinRpc, bean).fork();
//        } else {
//            System.out.println("to do, current size is " + taskList.size());
//            for (String taskName : taskList) {
//                Result temp = forkJoinRpc.cardBinVerify(bean);
//                result.put(temp.toString(), GsonUtils.toJson(temp));
//            }
//        }
        if (taskList == null || taskList.size() < 1 || taskList.isEmpty()) {
            Result temp = forkJoinRpc.cardBinVerify(bean);
            result.put(temp.toString(), String.valueOf(temp.size()));
        } else {
            for (String taskName : taskList) {
                new ForkJoinTask(forkJoinRpc, bean).fork();
            }
        }

        return result;
    }
}
