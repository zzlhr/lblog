package com.lhrsite.blog.entity;

import com.lhrsite.blog.consts.ArticleStatusConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import javax.persistence.*;

/**
 * 文章实体类
 * @author lhr
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 文章标题 */
    @NonNull
    private String articleTitle;

    /** 文章描述 */
    @NonNull
    private String articleDescribe;

    /** 文章内容 */
    @NonNull
    private String articleContentHtml;

    @NonNull
    private String articleContentMd;

    /** 文章状态 0为显示，1为隐藏 */
    private Integer articleStatus = ArticleStatusConst.SHOW;

    /** 文章作者 */
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "article_author", referencedColumnName = "id")
    private User articleAuthor;

    @Column(updatable = false, insertable = false)
    private java.util.Date createTime;

    @Column(updatable = false, insertable = false)
    private java.util.Date updateTime;

}
