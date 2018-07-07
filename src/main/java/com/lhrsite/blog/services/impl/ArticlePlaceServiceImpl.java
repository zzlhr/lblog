package com.lhrsite.blog.services.impl;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.ArticlePlace;
import com.lhrsite.blog.repository.ArticlePlaceRepository;
import com.lhrsite.blog.services.ArticlePlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 归档实现类
 *
 * @author lhr
 * @data 2018/4/26
 * @site https://www.lhrsite.com
 */
@Service
public class ArticlePlaceServiceImpl implements ArticlePlaceService {

    private final ArticlePlaceRepository articlePlaceRepository;

    @Autowired
    public ArticlePlaceServiceImpl(ArticlePlaceRepository
                                               articlePlaceRepository) {
        this.articlePlaceRepository = articlePlaceRepository;
    }

    @Override
    public ArticlePlace addPlace(ArticlePlace articlePlace) {

        if (articlePlace != null){
            return articlePlaceRepository.save(articlePlace);
        }

        throw new RuntimeException("添加存档 对象 不能为空！");
    }

    @Override
    public ArticlePlace updatePlace(ArticlePlace articlePlace) {
        if (articlePlace != null && articlePlace.getId() != null){
            return articlePlaceRepository.save(articlePlace);
        }
        throw new RuntimeException("修改存档 id/对象 不能为空！");
    }

    @Override
    public List<ArticlePlace> Top10Place() {

        PageRequest pageRequest = PageRequest.of(0,10,
                Sort.by(Sort.Order.desc("id")));

        return articlePlaceRepository
                .findAllByPlaceType(0, pageRequest)
                .getContent();
    }

    @Override
    public void place(Article article, int doType) {
        Calendar cal = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String placeTag = year + "/" + month;

        ArticlePlace articlePlace
                = articlePlaceRepository.findByPlaceTag(placeTag);
        if (doType == 0 && articlePlace == null){
            articlePlace = ArticlePlace.builder()
                    .placeTag(placeTag)
                    .placeType(0)
                    .placeValue("1")
                    .build();
        } else if (doType == 0){
            // 获取归档数
            Integer number
                    = Integer.parseInt(articlePlace.getPlaceValue());
            number += 1;
            articlePlace.setPlaceValue(String.valueOf(number));
        } else if (doType == 1 && articlePlace != null){
            Integer number
                    = Integer.parseInt(articlePlace.getPlaceValue());
            number -= 1;
            articlePlace.setPlaceValue(String.valueOf(number));
        }else {
            throw new RuntimeException("错误的归档操作.");
        }
        articlePlaceRepository.save(articlePlace);

    }

}
