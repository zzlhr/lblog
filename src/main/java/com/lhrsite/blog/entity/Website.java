package com.lhrsite.blog.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Builder
@Data
public class Website implements Serializable {

    @Tolerate
    public Website(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String host;
    private String name;
    private String keywords;
    private String description;
    private String masterName;
    private String masterEmail;

}
