package com.lhrsite.blog.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
public class ArticleStatistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String statisticsName;

    private Integer statisticsType;

    private String statisticsValue;

    @Column(insertable = false, updatable = false)
    private java.util.Date createTime;

    @Column(insertable = false, updatable = false)
    private java.util.Date updateTime;
}
