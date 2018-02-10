package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.FriendLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lhr
 * @create 2018/1/26
 */
@Repository
public interface FriendLinkRepository extends JpaRepository<FriendLink, Integer> {

    /**
     * 通过url查询
     * @param url
     * @return
     */
    FriendLink findByUrl(String url);


}
