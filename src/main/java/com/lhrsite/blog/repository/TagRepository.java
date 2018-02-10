package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lhr
 * @create 2018/1/25
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    /**
     * 通过标签内容查询
     * @param tagContent    标签内容
     * @return  标签
     */
    Tag findByTagContent(String tagContent);




}
