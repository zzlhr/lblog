package com.lhrsite.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lhr
 * @create 2018/2/3
 */
@Data
@AllArgsConstructor
public class AlertVO {


    public static String alert(int code, String msg){
        if (code >= 0){
            return "<script>alert('" + msg + "');location.href=history.back(-1);</script>";
        }else {
            return "<script>alert('" + msg + "');location.href=history.go(-1);</script>";
        }
    }


}
