package com.lhrsite.blog.util;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 刘浩然
 * @date 2017/8/17
 */

@Data
public class HttpClientUtil {

    private String url;

    private Boolean isPost = false;

    private Map<String,String> params = new HashMap<>();

    private Map<String,String> header = new HashMap<>();

    private String requst;

    private Map<String, List<String>> requestHeader;


    public HttpClientUtil url(String url){
        this.url = url;
        return this;
    }

    public HttpClientUtil get(){
        this.isPost = false;
        return this;
    }

    public HttpClientUtil post(){
        this.isPost = true;
        return this;
    }

    public HttpClientUtil param(String key, String value){
        this.params.put(key, value);
        return this;
    }

    public HttpClientUtil param(Map<String, String> param){
        this.params = param;
        return this;
    }


    public HttpClientUtil header(String key, String value){
        this.header.put(key, value);
        return this;
    }

    public HttpClientUtil header(Map<String, String> header){
        this.header = header;
        return this;
    }


    public Object send(){
        if (isPost){
            return sendPost(url,paramsListToStr(params), header);
        }else {
            System.out.println(url);
            return sendGet(url,paramsListToStr(params), header);
        }
    }


    private String paramsListToStr(Map<String, String> params){
        StringBuffer paramStr = new StringBuffer();
        Iterator it = params.entrySet().iterator();
        int i = 0;
        while(it.hasNext()){
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            paramStr.append(entry.getKey() + "=" + entry.getValue());
            paramStr.append("&");
            i++;
        }
        if (i > 0){
            return paramStr.substring(0, paramStr.length()-1);
        }
        return "";
    }




    private String sendGet(String url, String param, Map<String, String> header) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            Iterator it = header.entrySet().iterator();
            int i = 0;
            while(it.hasNext()){
                java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                System.out.println(entry.getKey().toString()+"-->"+ entry.getValue().toString());
                connection.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
            }

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            requestHeader = connection.getHeaderFields();
            // 遍历所有的响应头字段

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private String sendPost(String url, String param, Map<String, String> header) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            Iterator it = header.entrySet().iterator();
            int i = 0;
            while(it.hasNext()){
                java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                conn.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
            }

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();

            requestHeader = conn.getHeaderFields();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }



}
