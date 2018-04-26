package com.lhrsite.blog.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 文章归档对象
 * @author lhr
 */
@Data
@Entity
@Builder
public class ArticlePlace implements Serializable {

    @Tolerate
    public ArticlePlace (){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 归档标签 */
    private String placeTag;

    /** 归档值 */
    private String placeValue;

    /** 归档分类 */
    private Integer placeType;

}
