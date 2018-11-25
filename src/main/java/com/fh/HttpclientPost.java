package com.fh;

import com.fh.util.CheckSum;
import com.google.gson.Gson;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpclientPost {


    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("userName", "zhangLQ");
        map.put("age", "20");
        map.put("sex", "1");
        httpGet("http://www.baidu.com", map, null, true);
    }

    //get查询
    public static String httpGet(String url, Map<String, String> param, Map<String, String> header, boolean isValidate) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (param != null && param.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = param.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                pairs.add(new BasicNameValuePair(key, value));
            }

            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs);
                String entity = EntityUtils.toString(urlEncodedFormEntity, "utf-8");
                url += "?" + entity;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HttpGet httpGet = new HttpGet(url);
        buildHeader(header, httpGet, isValidate);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                    httpClient = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    //重构请求头
    private static void buildHeader(Map<String, String> header, HttpGet httpGet, boolean isValidate) {
        if (header != null && header.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpGet.addHeader(key, value);
            }
        }
        if (isValidate) {
            httpGet.addHeader("appKey", "2ea8eb2516404d76b7cf77cf90e234f0");
            String time = new Date().getTime() + "";
            httpGet.addHeader("time", time);
            String nonce = UUID.randomUUID().toString().replace("-", "").toUpperCase() + RandomStringUtils.randomAlphabetic(6) + new Date().getTime();
            httpGet.addHeader("nonce", nonce);
            httpGet.addHeader("appKey", "2ea8eb2516404d76b7cf77cf90e234f0");
            httpGet.addHeader("sign", CheckSum.getCheckSum("751932c8c6f04079a3729c2985cfbb9a", nonce, time));
        }

    }

    //post 新增
    public static String httpPost(String url, Map<String, String> params, Map<String, String> headers, boolean isValidate) {

        //打开浏览器
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        CloseableHttpResponse response = null;
        String result = "";
        if (null != params && params.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                pairs.add(new BasicNameValuePair(key, value));
            }
        }
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
            buildHeader(headers, httpPost, isValidate);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpPost) {
                httpPost.releaseConnection();
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                    httpClient = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return result;
    }

    private static void buildHeader(Map<String, String> headers, HttpPost httpPost, boolean isValidate) {
        if (null != headers && headers.size() > 0) {
            Iterator<Map.Entry<String, String>> header = headers.entrySet().iterator();
            while (header.hasNext()) {
                Map.Entry<String, String> next = header.next();
                String key = next.getKey();
                String value = next.getValue();
                httpPost.addHeader(key, value);
            }
        }
        if (isValidate) {
            httpPost.addHeader("appKey", "cdc38b19-8a5f-4dcc-af5c-bf488ce4e4f0");
            String time = new Date().getTime() + "";
            httpPost.addHeader("time", time);
            String nonce = UUID.randomUUID().toString().replace("-", "").toUpperCase() + RandomStringUtils.randomAlphabetic(6) + new Date().getTime();
            httpPost.addHeader("nonce", nonce);
            httpPost.addHeader("appKey", "cdc38b19-8a5f-4dcc-af5c-bf488ce4e4f0");
            httpPost.addHeader("sign", CheckSum.getCheckSum("ba51aeb9-6cac-40d0-b520-f09b98a32bf7", nonce, time));
        }
    }

    //put 修改
    public static String httpPut(String url, Map<String, String> param, Map<String, String> header, boolean isValidate) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut(url);

        // 请求的参数
        if (param != null && param.size() > 0) {
            Gson gson = new Gson();
            String paramJson = gson.toJson(param);
            StringEntity entity = new StringEntity(paramJson, "utf-8");
            entity.setContentType("application/json");
            httpPut.setEntity(entity);
        }
        //请求头
        buildHeader(header, httpPut, isValidate);
        //执行请求
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpPut != null) {
                httpPut.releaseConnection();
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                    httpClient = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static void buildHeader(Map<String, String> header, HttpPut httpPut, boolean isValidate) {
        if (header != null && header.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpPut.addHeader(key, value);
            }
        }
        if (isValidate) {
            httpPut.addHeader("appKey", "cdc38b19-8a5f-4dcc-af5c-bf488ce4e4f0");
            String time = new Date().getTime() + "";
            httpPut.addHeader("time", time);
            String nonce = UUID.randomUUID().toString().replace("-", "").toUpperCase() + RandomStringUtils.randomAlphabetic(10) + new Date().getTime();
            httpPut.addHeader("nonce", nonce);
            httpPut.addHeader("appKey", "cdc38b19-8a5f-4dcc-af5c-bf488ce4e4f0");
            httpPut.addHeader("sign", CheckSum.getCheckSum("ba51aeb9-6cac-40d0-b520-f09b98a32bf7", nonce, time));
        }
    }
}