package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.ArticleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述
 *
 * @author lhr
 * @data 2018/4/27
 * @site https://www.lhrsite.com
 */
@Repository
public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, Integer> {

}
