package com.lhrsite.blog.code;

import com.lhrsite.blog.exceptions.EncryptException;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
    public static String md5(String str) throws EncryptException {
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //加密后的字符串
            byte[] encryContext = md5.digest(str.getBytes("utf-8"));
            int i;
            StringBuffer buf = new StringBuffer("");
            //做相应的转化（十六进制）
            for (byte anEncryContext : encryContext) {
                i = anEncryContext;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EncryptException("编码失败！");
        }
    }



    public static String md5AddSalt(String str) throws EncryptException {
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
        return Arrays.toString(new Base64().encode(bstr));
    }
    public static String base64Encode(String str){
        return Arrays.toString(new Base64().encode(str.getBytes()));
    }
    /**
     * 解码base64
     * @param str   解码字符串
     * @return byte[]   解码字节
     */
    public static byte[] base64Decode(String str){
        byte[] bt = null;
        Base64 decoder = new Base64();
        bt = decoder.decode(str);
        return bt;
    }

}
