package com.lhrsite.blog;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.repository.ArticleRepository;
import com.lhrsite.blog.services.ArticlePlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private ArticlePlaceService articlePlaceService;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void contextLoads() {

    }

    @Test
    public void place(){
        List<Article> articleList = articleRepository.findAll();

        for (Article article : articleList){
            articlePlaceService.place(article, 0);
        }
    }


}
