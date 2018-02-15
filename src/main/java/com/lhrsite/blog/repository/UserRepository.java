package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lhr
 * @create 2018/1/25
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    /**
     * 通过用户名查询
     * @param userName  用户名
     * @return          用户
     */
    User findByUsername(String userName);

    User findByLoginName(String loginName);

}
