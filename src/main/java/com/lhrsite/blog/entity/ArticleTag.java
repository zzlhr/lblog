package com.lhrsite.blog.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
public class ArticleTag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer articleId;

    private String tagContent;
}
