package com.zhongan.comm.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * 利用HttpClient进行post请求的工具类
 * @author chenshiyang
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    /**
     * http连接池
     */
    private  static PoolingHttpClientConnectionManager connManager;
    //
    public final static String ACCEPT_CHARSET="accept-Charset";
    public final static String CHARSET="Charset";
    public final static String USER_AGENT="User-Agent";
    public final static String  CONNECTION="Connecction";
    public final static String  AUTHORIZATION="Authorization";
    public final static String CONTENT_TYPE = "Content-type";
    //
    public final static String TYPE_APPLICATION_JSON = "application/json";
    public final static String TYPE_APPLICATION_FORM = "application/x-www-form-urlencoded";
    public final static String TYPE_APPLICATION_MULTIPART_DATA = "multipart/form-data";


    //构造方法
    static
    {
        try {
            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = createIgnoreVerifySSL();
            //设置协议http和https对应的处理socket链接工厂的对象
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext))
                    .build();
            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connManager.setMaxTotal(200);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    /**
     * GET请求
     * @param url   资源地址
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String Get(String url)  throws  IOException
    {
        return Get(url,null);
    }


    /**
     * GET请求
     * @param url   资源地址
     * @param header header数据
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String Get(String url,Map<String,String> header)  throws IOException
    {
        return Get(url,header,null,null);
    }


    /**
     * GET请求
     * @param url   资源地址
     * @param header header数据
     * @param encoding  编码
     * @param sslContext
     * @return
     * @throws IOException
     */
    public static String Get(String url,Map<String,String> header, String encoding,SSLContext sslContext) throws  IOException {
        String result = "";
        if(StringUtils.isBlank(encoding))
            encoding="utf-8";
        logger.info("=======> HttpClient 请求地址："+url);
        logger.info("=======> HttpClient 请求方法：GET");
        logger.info("=======> HttpClient 请求Header："+JsonUtils.toJson(header));
        //创建自定义的httpclient对象
        CloseableHttpClient client = getHttpClient(sslContext);
        //创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        if(header!=null){
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        httpGet.setHeader(ACCEPT_CHARSET, encoding);
        httpGet.setHeader(CHARSET, encoding);
        logger.info("=======> HttpClient 准备完成");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpGet);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            result = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        logger.info("=======> HttpClient 请求完成："+result);
        return result;
    }


    /**
     * POST请求
     * @param url 资源地址
     * @param bodyDta BOdy数据
     * @return
     */
    public static String Post(String url, String bodyDta)  throws  IOException
    {
        return Post(url,null,bodyDta);
    }

    /**
     * POST请求
     * @param url 资源地址
     * @param bodyDta BOdy数据
     * @param sslContext
     * @return
     * @throws IOException
     */
    public static String Post(String url, String bodyDta,SSLContext sslContext)  throws  IOException
    {
        return Post(url,null,bodyDta,sslContext);
    }

    /**
     * POST请求
     * @param url 资源地址
     * @param header header数据
     * @param bodyDta BOdy数据
     * @return
     */
    public static String Post(String url, Map<String,String> header,String bodyDta)  throws  IOException
    {
        return Post(url,header,null,bodyDta,null,null);
    }

    /**
     * POST请求
     * @param url 资源地址
     * @param header header数据
     * @param bodyDta BOdy数据
     * @param sslContext
     * @return
     * @throws IOException
     */
    public static String Post(String url, Map<String,String> header,String bodyDta,SSLContext sslContext)  throws  IOException
    {
        return Post(url,header,null,bodyDta,null,sslContext);
    }

    /**
     * POST请求
     * @param url 资源地址
     * @param header header数据
     * @param formData BOdy数据
     * @return
     */
    public static String Post(String url, Map<String,String> header, Map<String,String> formData)  throws  IOException
    {
        return Post(url,header,formData,null,null,null);
    }

    /**
     * POST请求
     * @param url   资源地址
     * @param formData   参数列表
     * @param bodyData   BOdy数据
     * @param header header数据
     * @param encoding  编码
     * @param sslContext
     * @return
     * @throws IOException
     */
    public static String Post(String url,Map<String,String> header, Map<String,String> formData,String bodyData, String encoding,SSLContext sslContext) throws  IOException {
        String result = "";
        if(StringUtils.isBlank(bodyData))
            bodyData="";
        if(StringUtils.isBlank(encoding))
            encoding="utf-8";

        logger.info("=======> HttpClient 请求地址："+url);
        logger.info("=======> HttpClient 请求方法：POST");
        logger.info("=======> HttpClient 请求FormData："+JsonUtils.toJson(formData));
        logger.info("=======> HttpClient 请求BodyData："+bodyData.toString());
        logger.info("=======> HttpClient 请求Header："+JsonUtils.toJson(header));

        //创建自定义的httpclient对象
        CloseableHttpClient client = getHttpClient(sslContext);

        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(formData!=null&& formData.size()>0){
            for (Map.Entry<String, String> entry : formData.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
        }
        else {
            httpPost.setEntity(new StringEntity(bodyData.toString(),encoding));
        }

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        if(header!=null){
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        else
        {
            httpPost.setHeader(CONTENT_TYPE,TYPE_APPLICATION_JSON);
        }
        httpPost.setHeader(ACCEPT_CHARSET, encoding);
        httpPost.setHeader(CHARSET, encoding);
        logger.info("=======> HttpClient 准备完成");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            result = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        logger.info("=======> HttpClient 请求完成："+result);
        return result;
    }

    /**
     * 获取HttpClient
     * @param sslcontext  如果为NULL则返回默认Client
     * @return
     */
    public static CloseableHttpClient getHttpClient(SSLContext sslcontext)
    {
        //如果指定SSL能使用指定SSL连接
        if(null!=sslcontext) {
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            return HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
        }

        //返回默认的client
        return  HttpClients.custom().setConnectionManager(connManager).build();
    }


    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
}