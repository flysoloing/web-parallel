package com.flysoloing.parallel.forkjoin;

import com.flysoloing.parallel.common.BusinessBean;
import com.flysoloing.parallel.common.Result;

import java.util.Random;

/**
 * <br>
 * User: laitao<br>
 * Date: 14-10-9<br>
 * Time: 下午1:54<br>
 */
public class ForkJoinRpc {

    public Result method0(BusinessBean bean) {
        try {
            System.out.println("method 0 sleep 1000 ms");
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method1(BusinessBean bean) {
        try {
            System.out.println("method 1 sleep 100 ms");
            Thread.sleep(100l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method2(BusinessBean bean) {
        try {
            System.out.println("method 2 sleep 200 ms");
            Thread.sleep(200l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method3(BusinessBean bean) {
        try {
            System.out.println("method 3 sleep 300 ms");
            Thread.sleep(300l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method4(BusinessBean bean) {
        try {
            System.out.println("method 4 sleep 400 ms");
            Thread.sleep(400l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method5(BusinessBean bean) {
        try {
            System.out.println("method 5 sleep 500 ms");
            Thread.sleep(500l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method6(BusinessBean bean) {
        try {
            System.out.println("method 6 sleep 600 ms");
            Thread.sleep(600l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method7(BusinessBean bean) {
        try {
            System.out.println("method 7 sleep 700 ms");
            Thread.sleep(700l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method8(BusinessBean bean) {
        try {
            System.out.println("method 8 sleep 800 ms");
            Thread.sleep(800l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result method9(BusinessBean bean) {
        try {
            System.out.println("method 9 sleep 900 ms");
            Thread.sleep(900l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public Result cardBinVerify(BusinessBean bean) {
        Random random = new Random();
        long total = random.nextInt(20) * 1l;
        try {
            Thread.sleep(total);
            System.out.println("this remote method call spent " + total + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    public static void main(String[]args) throws Exception {
        ForkJoinRpc forkJoinRpc = new ForkJoinRpc();
        forkJoinRpc.cardBinVerify(new BusinessBean());
    }

}
