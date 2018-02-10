package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lhr
 * @create 2018/1/26
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

}
