package com.lhrsite.blog.services;

import com.lhrsite.blog.entity.User;

/**
 * @author lhr
 * @create 2018/2/3
 */
public interface UserService {

    /**
     * 登录
     * @param userName    用户名
     * @param password    密码
     * @return            登录用户
     */
    User login(String userName, String password);

    /**
     * 是否登录
     * @param token     登录令牌
     * @param userName  用户名
     * @return          正确返回true，错误返回false
     */
    Boolean isLogin(String token, String userName);


}
