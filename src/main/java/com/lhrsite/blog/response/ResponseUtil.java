package com.lhrsite.blog.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * response工具类
 *
 * @author lhr
 * @data 2018/4/11
 * @site https://www.lhrsite.com
 */
public class ResponseUtil {

    /**
     * 通过response返回图片
     * @param response   HttpServletResponse对象
     * @param imageByte  图片字节集
     * @throws IOException
     */
    public static void responseImage(
            HttpServletResponse response, byte[] imageByte)
            throws IOException
    {
        // 设置ContentType
        response.setContentType("image/*");
        // 获取输出流
        OutputStream stream = response.getOutputStream();
        // 输入字节
        stream.write(imageByte);
    }



}
