package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 站点信息
 *
 * @author lhr
 * @data 2018/5/3
 * @site https://www.lhrsite.com
 */
@Repository
public interface WebsiteRepository extends JpaRepository<Website, Integer> {


}
