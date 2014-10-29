package com.flysoloing.parallel.threadpool;

import com.flysoloing.parallel.common.BusinessBean;
import com.flysoloing.parallel.common.Result;

import java.util.Random;

/**
 * <br>
 * User: laitao<br>
 * Date: 14-10-11<br>
 * Time: 上午10:24<br>
 */
public class ThreadPoolRpc {

//    public Result cardBinVerify(BusinessBean bean) {
//        Result result = new Result();
//        long start = System.currentTimeMillis();
//        CardBinRequestVo requestVo = new CardBinRequestVo();
//        requestVo.setCardNo("621909");
//
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://center.pay.jd.com/service/center/verifyCardBin");
//        httpPost.setHeader("Content-Type", "application/json");
//        httpPost.setHeader("token", "1qaz2wsx3edc");
//
//        List<NameValuePair> formParams = new ArrayList<>();
//        formParams.add(new BasicNameValuePair("data", GsonUtils.toJson(requestVo)));
//        UrlEncodedFormEntity urlEncodedFormEntity;
//        System.out.println("thread id: " + Thread.currentThread().getId() + ", request data:" + GsonUtils.toJson(requestVo));
//
//        String content = "";
//        try {
//            urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
//            httpPost.setEntity(urlEncodedFormEntity);
////            System.out.println("executing request:" + httpPost.getURI());
//
//            HttpResponse httpResponse = null;
//            httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//
//            if (httpEntity != null) {
//                content = EntityUtils.toString(httpEntity, "UTF-8");
//                System.out.println("thread id: " + Thread.currentThread().getId() + ", response content:" + content);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println("this remote method call spent " + (end - start) + " ms");
//
//        result.put(String.valueOf(content.hashCode()), content);
//        return result;
//    }

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
}
