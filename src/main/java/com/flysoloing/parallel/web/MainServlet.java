package com.flysoloing.parallel.web;

import com.flysoloing.parallel.common.BusinessBean;
import com.flysoloing.parallel.forkjoin.ForkJoinTask;
import com.flysoloing.parallel.common.Result;
import com.flysoloing.parallel.forkjoin.ForkJoinRpc;
import com.flysoloing.parallel.threadpool.ThreadPoolService;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 * User: laitao<br>
 * Date: 14-10-9<br>
 * Time: 上午11:15<br>
 */
public class MainServlet extends HttpServlet {



    @Override
    public void init() throws ServletException {
        //初始化方案三所需ForkJoinPool
    }

    /**
     * 背景：一个业务请求的应答需要调用多个（例如：10）后台服务接口，一般来说都是串行同步调用，总调用时长为各个服务接口调用时长之和
     * 目标：通过对web请求的后台调用进行并行化处理，降低总调用时长
     *
     * 方案一：Servlet 3.0
     *
     *
     *
     * 方案二：ThreadPoolExecutor & Feature
     *
     *
     *
     * 方案三：fork/join
     *
     *
     *
     * 方案四：actor模型
     *
     *
     *
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String type = req.getParameter("type");
        Result result = new Result();

        List<String> taskList = new ArrayList<>();
        taskList.add("method0");
        taskList.add("method1");
        taskList.add("method2");
        taskList.add("method3");
        taskList.add("method4");
        taskList.add("method5");
        taskList.add("method6");
        taskList.add("method7");
        taskList.add("method8");
        taskList.add("method9");

        BusinessBean bean = new BusinessBean();
        bean.put("1", req.getParameter("1"));

        //方案一
        if (type.equals("1")) {
            System.out.println("case 1 is running ...");
            long start = System.currentTimeMillis();

            PrintWriter writer = resp.getWriter();
            writer.println("main business flow over, time is " + new Date());
            writer.flush();

            final AsyncContext context = req.startAsync();
            Runnable task = new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(2000l);
                        PrintWriter printWriter = context.getResponse().getWriter();
                        printWriter.println("async business flow over, time is " + new Date());
                        context.complete();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(task).start();

            writer.println("all business flow over, time is " + new Date());
            writer.flush();

            long end = System.currentTimeMillis();
            System.out.println("servlet async total time is " + (end - start) + " ms");
        }

        //方案二
        if (type.equals("2")) {
            System.out.println("case 2 is running ...");
            long start = System.currentTimeMillis();

            ThreadPoolService threadPoolService = new ThreadPoolService();
            result = threadPoolService.threadPoolService(taskList, bean);

            long end = System.currentTimeMillis();
            System.out.println("thread pool total time is " + (end - start) + " ms");
        }

        //方案三
        if (type.equals("3")) {
            System.out.println("case 3 is running ...");
            long start = System.currentTimeMillis();

            ForkJoinRpc forkJoinRpc = new ForkJoinRpc();

            ForkJoinTask forkJoinTask = new ForkJoinTask(taskList, forkJoinRpc, bean);
            result = forkJoinPool.invoke(forkJoinTask);
//            forkJoinPool.submit(forkJoinTask);
            forkJoinPool.shutdown();
            try {
                forkJoinPool.awaitTermination(1000l, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            while (!forkJoinTask.isDone()) {
//                System.out.print(" .");
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            long end = System.currentTimeMillis();
            System.out.println("fork/join total time is " + (end - start) + " ms");

        }

        //方案四
        if (type.equals("4")) {
            System.out.println("case 4 is running ...");
        }

        //最后处理
        System.out.println("result size is " + result.size());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
