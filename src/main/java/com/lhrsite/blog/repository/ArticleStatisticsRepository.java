package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.ArticleStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lhr
 * @create 2018/2/7
 */
@Repository
public interface ArticleStatisticsRepository extends JpaRepository<ArticleStatistics, Integer> {

}
