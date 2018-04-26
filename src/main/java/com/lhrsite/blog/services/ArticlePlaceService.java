package com.lhrsite.blog.services;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.ArticlePlace;

import java.util.List;

/**
 * 描述
 *
 * @author lhr
 * @data 2018/4/26
 * @site https://www.lhrsite.com
 */
public interface ArticlePlaceService {

    /**
     * 添加归档记录
     * @param articlePlace  归档对象
     * @return 添加的归档对象
     */
    ArticlePlace addPlace(ArticlePlace articlePlace);


    /** 修改的归档记录 */
    ArticlePlace updatePlace(ArticlePlace articlePlace);



    /** 获取前十的归档记录 */
    List<ArticlePlace> Top10Place();

    /**
     * 对文章进行归档
     * @param article   文章对象
     * @param doType    操作类型 0 为添加文章时加归档数量，1 为删除文章时减去的归档数量
     */
    void place(Article article, int doType);

}
