package com.lhrsite.blog.services.impl;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.ArticleTag;
import com.lhrsite.blog.entity.Tag;
import com.lhrsite.blog.repository.ArticleTagRepository;
import com.lhrsite.blog.repository.TagRepository;
import com.lhrsite.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhr
 * @create 2018/1/31
 */
@Service
public class TagServiceImpl implements TagService {


    private final TagRepository repository;

    private final ArticleTagRepository articleTagRepository;

    @Autowired
    public TagServiceImpl(TagRepository repository, ArticleTagRepository articleTagRepository) {
        this.repository = repository;
        this.articleTagRepository = articleTagRepository;
    }


    @Override
    public List<Tag> getTopTenTag() {

        return repository.findAll(
                new PageRequest(0,10,
                        new Sort(Sort.Direction.DESC, "tagTime"))).getContent();
    }

    @Override
    public List<Tag> getAll() {
        return repository.findAll();
    }

    @Override
    public Integer addOneTime(String tagContent) {
        Tag tag = repository.findByTagContent(tagContent);
        if (tag == null){
            tag = new Tag();
            tag.setTagContent(tagContent);
            tag.setTagTime(1);

        }else {
            tag.setTagTime(tag.getTagTime() + 1);
        }

        return repository.save(tag).getId();
    }

    @Override
    public void delectTag(Article article) {
        List<ArticleTag> articleTags
                = articleTagRepository
                .findAllByArticleId(article.getId());

        List<String> tags = new ArrayList<>();
        for (ArticleTag articleTag : articleTags){
            tags.add(articleTag.getTagContent());
        }

        List<Tag> tagList = repository.findAllByTagContent(tags);
        for (Tag tag : tagList){
            tag.setTagTime(tag.getTagTime() - 1);
        }
        repository.saveAll(tagList);



        articleTagRepository.deleteAllByArticleId(article.getId());
    }


    @Override
    public Page<ArticleTag> findArticleTag(String tag,
                                           Pageable pageable) {
        return articleTagRepository.findByTagContent(tag, pageable);
    }
}
