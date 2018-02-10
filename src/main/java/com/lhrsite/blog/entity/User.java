package com.lhrsite.blog.entity;

import com.lhrsite.blog.consts.UserStatusConst;
import com.lhrsite.blog.code.Encrypt;
import com.lhrsite.blog.exceptions.EncryptException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /** 用户 */
    @NonNull
    private String username;

    /** 密码 */
    @NonNull
    private String password;

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


    public void setPassword(String password){
        try {
            this.password = Encrypt.base64Encode(Encrypt.md5AddSalt(password));
        } catch (EncryptException e) {
            e.printStackTrace();
        }
    }

}
