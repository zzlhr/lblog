package com.lhrsite.blog.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ArticleStatistics implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String statisticsName;

    private Integer statisticsType;

    private String statisticsValue;

    @Column(insertable = false, updatable = false)
    private java.util.Date createTime;

    @Column(insertable = false, updatable = false)
    private java.util.Date updateTime;
}
