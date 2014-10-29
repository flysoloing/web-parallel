package com.flysoloing.parallel.threadpool;

import com.flysoloing.parallel.common.BusinessBean;
import com.flysoloing.parallel.common.Result;
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
 * Date: 14-10-11<br>
 * Time: 上午10:24<br>
 */
public class ThreadPoolRpc {

    public Result cardBinVerify(BusinessBean bean) {
        Result result = new Result();
        long start = System.currentTimeMillis();
        CardBinRequestVo requestVo = new CardBinRequestVo();
        requestVo.setCardNo("621909");

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://www.baidu.com");
        httpPost.setHeader("Content-Type", "application/json");

        List<NameValuePair> formParams = new ArrayList<>();
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
}
