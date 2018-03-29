package com.lhrsite.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private Integer userId;
    private String userIp;
    private String operation;

    @Column(updatable = false, insertable = false)
    private java.util.Date createTime;


    public Log(String userName, Integer userId, String userIp, String operation){
        this.userName = userName;
        this.userId = userId;
        this.userIp = userIp;
        this.operation = operation;
    }

    public Log(String userIp, String operation){
        this.userIp = userIp;
        this.operation = operation;
    }

}
