package com.lhrsite.blog.vo;

import com.lhrsite.blog.entity.ArticleTag;
import com.lhrsite.blog.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章视图
 * @author lhr
 * @create 2018/1/27
 */
@Data
public class ArticleVO {


    private Integer id;

    /** 文章标题 */
    private String articleTitle;

    /** 文章描述 */
    private String articleDescribe;

    /** 文章内容 */
    private String articleContentMd;
    private String articleContentHtml;
    /** 文章状态 0为显示，1为隐藏 */
    private Integer articleStatus;

    /** 文章作者 */
    private User articleAuthor;

    private java.util.Date createTime;

    private java.util.Date updateTime;

    private List<ArticleTag> tagList = new ArrayList<>();

}
