package com.lhrsite.blog;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.ArticleInfo;
import com.lhrsite.blog.repository.ArticleInfoRepository;
import com.lhrsite.blog.repository.ArticleRepository;
import com.lhrsite.blog.services.ArticlePlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private ArticlePlaceService articlePlaceService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleInfoRepository articleInfoRepository;

    @Test
    public void contextLoads() {

    }

    //*********** 工具箱 ************//

    /**
     * 归档工具箱
     * @use 使用说明：2.0以下升级请使用调用该方法。
     */
    @Test
    public void place(){
        //
        List<Article> articleList = articleRepository.findAll();

        for (Article article : articleList){
            articlePlaceService.place(article, 0);
        }
    }



    @Test
    public void initArticleInfo(){
        List<Article> articleList = articleRepository.findAll();
        List<ArticleInfo> articleInfos = new ArrayList<>();
        for (Article article : articleList){
            articleInfos.add(ArticleInfo.builder().articleId(article.getId()).build());
        }
        articleInfoRepository.saveAll(articleInfos);

    }


}
