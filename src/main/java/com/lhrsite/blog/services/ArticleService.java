package com.lhrsite.blog.services;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.ArticleComment;
import com.lhrsite.blog.entity.ArticleInfo;
import com.lhrsite.blog.entity.User;
import com.lhrsite.blog.vo.ArticleVO;
import com.lhrsite.blog.vo.PageContentVO;
import com.lhrsite.blog.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 文章操作
 * @author lhr
 * @create 2018/1/26
 */
public interface ArticleService {


    /**
     * 获取首页文章
     * @param ip    操作ip
     * @return  首页文章
     */
    List<ArticleVO> getIndexArticle(String ip);


    /**
     * 获取文章列表
     * @param ip    操作ip
     * @param page  分页
     * @return      本页文章
     */
    PageContentVO<ArticleVO> getArticleList(String ip, PageRequest page);

    PageContentVO<ArticleVO> getArticleList(String keyword, String ip, PageRequest page);

    PageContentVO<ArticleVO> getArticleList(String ip, PageRequest page, Integer status);

    /**
     * 获取文章详情
     * @param ip    操作ip
     * @param id    文章id
     * @return      文章详情
     */
    ArticleVO getArticleInfo(String ip, int id);

    /**
     * 修改文章状态
     * @param ip    操作ip
     * @param status    状态
     * @param articleId 文章id
     * @param user      修改改状态的用户
     * @return          文章对象
     *
     */
    ArticleVO updateArticleStatus(String ip, Integer status, Integer articleId, User user);

    /**
     * 修改文章
     * @param ip    操作ip
     * @param article       文章对象
     * @param user          修改的用户
     * @return              修改后的文章对象
     */
    ArticleVO updateArticle(String ip, Article article, String[] tagContents, User user);

    /**
     * 删除文章-修改状态
     * @param ip    操作ip
     * @param article   文章对象
     * @param user      用户
     * @return          删除后的文章对象
     */
    ArticleVO delectArticle(String ip, Article article, User user);

    /**
     * 添加文章
     * @param ip    操作ip
     * @param article   文章对象
     * @param tagContents 一组标签内容
     * @param user      添加的用户
     * @return          添加成功的文章对象
     */
    ArticleVO addArticle(String ip, Article article, String[] tagContents, User user);


    /**
     * 发表一条评论
     * @param articleComment    评论对象
     * @return      是否成功
     */
    boolean sendComment(ArticleComment articleComment);


    /**
     * 获取文章评论
     * @param articleId 文章id
     * @param pageRequest   分页
     * @return  评论对象
     */
    Page<ArticleComment> getArticleComments(Integer articleId, PageRequest pageRequest);

    List<ArticleVO> getArticleByIds(List<Integer> ids);

    /**
     * 通过年月查询文章
     * @param year      年
     * @param month     月
     * @param pageable  分页
     * @return          文章对象
     */
    PageContentVO<ArticleVO> findArticleByYearAndMonth(
            String year, String month, Pageable pageable);


    /**
     * 更新文章详情（点击数，点赞数，评论数）
     * @param type 0点击，1点赞，2评论
     * @param articleId 文章id
     * @return 返回更新后的文章详情
     */
    ArticleInfo upInfo(Integer type, Integer articleId);

}
