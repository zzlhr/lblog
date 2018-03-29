package com.lhrsite.blog.entity;

import com.lhrsite.blog.consts.CommentStatusConst;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 文章评论
 * @author lhr
 */
@Entity
@Data
@ToString
public class ArticleComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 文章id */
    private Integer articleId;

    /** 用户id */
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /** 评论内容 */
    private String commentContent;

    /** 评论状态 */
    private Integer commentStatus = CommentStatusConst.SHOW;

    /** 评论ip */
    private String commentIp;

    @Column(updatable = false, insertable = false)
    private Date createTime;

    @Column(updatable = false, insertable = false)
    private Date updateTime;


}
