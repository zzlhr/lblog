package com.lhrsite.blog.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Builder
public class ArticleInfo implements Serializable {

    @Tolerate
    public ArticleInfo(){}

    @Id
    private Integer articleId;

    private Integer articleClick = 0;

    private Integer articleCommemt = 0;

    private Integer articleZan = 0;

}
