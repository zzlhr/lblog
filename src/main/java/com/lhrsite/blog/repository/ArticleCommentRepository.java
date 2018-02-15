package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lhr
 * @create 2018/2/10
 */
@Repository
public interface ArticleCommentRepository
        extends JpaRepository<ArticleComment, Integer> {

    /** 通过文章id和文章状态查询 */
    Page<ArticleComment> findByArticleIdAndCommentStatus(Integer articleId,
                                                         Integer commentStatus,
                                                         Pageable pageable);






}
