package com.lhrsite.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendLink implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String value;

    private String url;

    private Integer status = 0;

}
