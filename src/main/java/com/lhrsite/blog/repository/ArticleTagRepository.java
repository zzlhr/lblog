package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.ArticleTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lhr
 * @create 2018/1/25
 */
@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Integer> {

    List<ArticleTag> findAllByArticleId(Integer articleId);

    List<ArticleTag> findAllByArticleIdIsIn(List<Integer> articleId);

    void deleteAllByArticleId(Integer articleId);

    Page<ArticleTag> findByTagContent(String tagContent,
                                      Pageable pageable);

}
