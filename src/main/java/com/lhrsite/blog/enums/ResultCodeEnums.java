package com.lhrsite.blog.enums;

/**
 * @author lhr
 * @create 2018/2/2
 */
public enum  ResultCodeEnums {

    UPLOAD_PICTURE_SUCCESS(13000,"图片上传成功！"),
    UPLOAD_PICTURE_ERROR(13001,"图片上传失败！"),
    ;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCodeEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
