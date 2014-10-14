package com.flysoloing.parallel.rpc;

import com.flysoloing.parallel.domain.BusinessBean;
import com.flysoloing.parallel.domain.Result;
import com.jd.payment.center.export.vo.CardBinRequestVo;
import com.jd.payment.paycommon.utils.GsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

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
        Result result = new Result();
        long start = System.currentTimeMillis();
        CardBinRequestVo requestVo = new CardBinRequestVo();
        requestVo.setCardNo("621909");

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://center.pay.jd.com/service/center/verifyCardBin");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("token", "1qaz2wsx3edc");

        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("data", GsonUtils.toJson(requestVo)));
        UrlEncodedFormEntity urlEncodedFormEntity;
        System.out.println("thread id: " + Thread.currentThread().getId() + ", request data:" + GsonUtils.toJson(requestVo));

        String content = "";
        try {
            urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
//            System.out.println("executing request:" + httpPost.getURI());

            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity != null) {
                content = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("thread id: " + Thread.currentThread().getId() + ", response content:" + content);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("this remote method call spent " + (end - start) + " ms");

        result.put(String.valueOf(content.hashCode()), content);
        return result;
    }

    public static void main(String[]args) throws Exception {
        ForkJoinRpc forkJoinRpc = new ForkJoinRpc();
        forkJoinRpc.cardBinVerify(new BusinessBean());
    }

}
