package com.lhrsite.blog.repository;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


/**
 * @author lhr
 * @create 2018/1/25
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /**
     * 通过id查找文章，
     * <p>
     * 该方法适用于<b>管理员操作</b>，
     * 因为该方法没有判断文章状态
     * </p>
     * @param id    文章id
     * @return      文章对象
     */
    Article findById(Integer id);

    /**
     * 通过文章id和文章状态查询
     * @param id        文章id
     * @param status    文章状态
     * @return     查询到的文章对象
     */
    Article findByIdAndArticleStatus(Integer id, Integer status);


    /**
     * 通过文章作者查询
     * @param articleAuthor     文章作者对象，只需要传id
     * @param pageable          分页
     * @return                  带分页的文章对象
     */
    Page<Article> findAllByArticleAuthor(User articleAuthor, Pageable pageable);


    /**
     * 通过状态进行分页查询
     * @param articleStatus    文章状态
     * @param pageable         分页
     * @return                 查询到的文章对象
     */
    Page<Article> findAllByArticleStatus(Integer articleStatus, Pageable pageable);


    /**
     * 通过文章标题和文章状态匹配
     * @param articleStatus 文章状态
     * @param articleTitle  文章标题
     * @param pageable      文章分页
     * @return              查询的文章对象
     */
    Page<Article> findAllByArticleStatusAndArticleTitleLike(
            Integer articleStatus,
            String articleTitle,
            Pageable pageable);


    /**
     * 根据事件区间查询
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param pageable  分页对象
     * @return          文章
     */
    Page<Article> findByCreateTimeBetween(Date beginTime, Date endTime, Pageable pageable);


}
