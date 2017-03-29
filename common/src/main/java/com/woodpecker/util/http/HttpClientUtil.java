package com.woodpecker.util.http;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.util.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 *
 * @author Glenn
 * @since 2017-03-27
 */
public final class HttpClientUtil {
    /**
     * get请求
     *
     * @param url    URL
     * @param config 请求配置
     * @return 结果
     * @throws HttpClientException 获取响应失败
     */
    public static String doGet(String url, HttpClientConfig config) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpclient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpGet.setConfig(config.buildRequestConfig());
            config.getHeader().forEach(httpGet::addHeader);
            httpGet.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            Logger.error("The http request to acquire the response failed.", e);
            // Exception transition
            throw new HttpClientException(e);
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                Logger.error("Close the http client failed.", e);
                // Exception transition
                throw new HttpClientException(e);
            }
        }
    }

    /**
     * JSON的post请求
     *
     * @param url    URL
     * @param json   {@code JSON} 参数
     * @param config 请求配置
     * @return 结果
     * @throws HttpClientException 获取响应失败
     */
    public static String doJsonPost(String url, String json, HttpClientConfig config) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpclient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpPost.setConfig(config.buildRequestConfig());
            config.getHeader().forEach(httpPost::addHeader);
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            httpPost.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());

            EntityBuilder entityBuilder = EntityBuilder.create();
            entityBuilder.setText(json);
            entityBuilder.setContentType(ContentType.APPLICATION_JSON);
            entityBuilder.setContentEncoding(config.getCharset());

            HttpEntity requestEntity = entityBuilder.build();
            httpPost.setEntity(requestEntity);

            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            Logger.error("The http request to acquire the response failed.", e);
            // Exception transition
            throw new HttpClientException(e);
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                Logger.error("Close the http client failed.", e);
                // Exception transition
                throw new HttpClientException(e);
            }
        }
    }

    /**
     * Map的post请求
     *
     * @param url    URL
     * @param body   {@code Map}参数
     * @param config 请求配置
     * @return 结果
     * @throws HttpClientException 获取响应失败
     */
    public static String doMapPost(String url, Map<String, String> body, HttpClientConfig config) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpclient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpPost.setConfig(config.buildRequestConfig());
            config.getHeader().forEach(httpPost::addHeader);
            httpPost.addHeader(HTTP.CONTENT_ENCODING, config.getCharset());
            if (body != null) {
                List<NameValuePair> nvp = new ArrayList<>();
                body.forEach((key, value) -> {
                    nvp.add(new BasicNameValuePair(key, value));
                });
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nvp, config.getCharset()));
                } catch (Exception e) {
                    Logger.error("translate the code failed.", e);
                    throw new HttpClientException(e);
                }
            }
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            Logger.error("The http request to acquire the response failed.", e);
            // Exception transition
            throw new HttpClientException(e);
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                Logger.error("Close the http client failed.", e);
                // Exception transition
                throw new HttpClientException(e);
            }
        }
    }

    /**
     * 文件post请求
     *
     * @param url    URL
     * @param arg    {@code JSON} 参数
     * @param files  文件{@code List<FileBody>}
     * @param config 请求配置
     * @return 结果
     * @throws HttpClientException 获取响应失败
     */
    public static String doFilePost(String url, JSONObject arg, List<FileBody> files, HttpClientConfig config) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpclient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        if (config == null) {
            config = new HttpClientConfig();
        }
        try {
            httpPost.setConfig(config.buildRequestConfig());
            config.getHeader().forEach(httpPost::addHeader);
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            multipartEntity.setCharset(Charset.defaultCharset());
            arg.keySet().forEach((key) ->
                    multipartEntity.addTextBody(key, arg.getString(key),
                            ContentType.create(ContentType.APPLICATION_JSON.getMimeType(), Charset.forName("UTF-8")))
            );
            for (FileBody body : files) {
                multipartEntity.addPart("files", body);
            }

            // set the request context
            httpPost.setEntity(multipartEntity.build());
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            Logger.error("The http request to acquire the response failed.", e);
            // Exception transition
            throw new HttpClientException(e);
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                Logger.error("Close the http client failed.", e);
                // Exception transition
                throw new HttpClientException(e);
            }
        }
    }

    /**
     * 表单post请求
     *
     * @param params 参数
     * @param url    URL
     * @return response
     */
    public static String doFormPost(Map<String, String> params, String url) {
        StringBuffer buffer = new StringBuffer();
        OutputStream outputStream = null;
        BufferedReader in = null;
        try {

            URL realURL = new URL(url);
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    /*translate code*/
                    buffer.append(entry.getKey())
                          .append("=")
                          .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                          .append("&");
                }
                /*clear out the '&' at lastest index*/
                buffer.deleteCharAt(buffer.length() - 1);
            }
            HttpURLConnection urlConnection = (HttpURLConnection) realURL.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setRequestMethod("POST");
            /*acquire the data from the server*/
            urlConnection.setDoInput(true);
            /*write data into the server*/
            urlConnection.setDoOutput(true);
            //获得上传信息的字节大小以及长度
            byte[] request = buffer.toString().getBytes();
            //表示设置请求体的类型是文本类型
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", String.valueOf(request.length));
            //获得输出流,向服务器输出数据
            outputStream = urlConnection.getOutputStream();
            outputStream.write(request, 0, request.length);
            outputStream.close();
            //获得服务器响应的结果和状态码
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String result = "";
                String temp;
                while ((temp = in.readLine()) != null) {
                    result += temp;
                }
                return result;
            }
        } catch (Exception e) {
            throw new HttpClientException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return "";
    }

}
