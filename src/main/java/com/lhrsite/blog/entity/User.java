package com.lhrsite.blog.entity;

import com.lhrsite.blog.consts.UserStatusConst;
import com.lhrsite.blog.code.Encrypt;
import com.lhrsite.blog.exceptions.EncryptException;
import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
@Builder
public class User implements Serializable {

    @Tolerate
    public User(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String loginName;

    /** 用户 */
    @NonNull
    private String username;

    /** 密码 */
    private String password;

    /** 头像地址 */
    private String headerUrl;

    /** 创建时间 */
    @Column(updatable = false, insertable = false)
    private java.util.Date createTime;


    /** 更新时间 */
    @Column(updatable = false, insertable = false)
    private java.util.Date updateTime;

    /** 最后登录ip */
    private String lastLoginIp;

    /** 登录地址 */
    private String lastLoginAddress;

    /** 登录令牌 */
    private String token;

    /** 状态 */
    private Integer status = UserStatusConst.USE;

    /** 是否为管理员 */
    private Integer admin;

    /** 账号类型 */
    private Integer type;


    public void setPassword(String password){
        try {
            this.password = Encrypt.md5AddSalt(password);
        } catch (EncryptException e) {
            e.printStackTrace();
        }
    }

}
