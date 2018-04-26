package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.ArticlePlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * @author lhr
 * @create 2018/1/25
 */
@Repository
public interface ArticlePlaceRepository extends JpaRepository<ArticlePlace, Integer> {

    /**
     * 通过归档类型查询。
     * @param placeType 归档类型
     * @return  查询结果
     */
    Page<ArticlePlace> findAllByPlaceType(Integer placeType, Pageable pageable);

    ArticlePlace findByPlaceTag(String placeTag);



}
