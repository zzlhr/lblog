package com.lhrsite.blog.code;

import com.lhrsite.blog.exceptions.EncryptException;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 编码
 * @author 刘浩然
 * @date 2017/7/26
 */
public class Encrypt {

    private static final String SALT = "sfhugeiowe%$^$$#@#";


    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     */
    public static byte[] md5(String str) throws EncryptException {
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //加密后的字符串
            return md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EncryptException("编码失败！");
        }
    }



    public static byte[] md5AddSalt(String str) throws EncryptException {
        //加盐
        str = str + SALT;
        return md5(str);
    }


    /**
     * 编码 base64
     * @param bstr      编码字节
     * @return String   编码结果
     */
    public static String base64Encode(byte[] bstr){
        return new BASE64Encoder().encode(bstr);
    }

    /**
     * 解码base64
     * @param str   解码字符串
     * @return byte[]   解码字节
     */
    public static byte[] base64Decode(String str) throws EncryptException {
        byte[] bt = null;
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            throw new EncryptException("编码失败！");
        }
        return bt;
    }


}
