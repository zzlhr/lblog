package com.lhrsite.blog.services.impl;

import com.lhrsite.blog.entity.Tag;
import com.lhrsite.blog.repository.TagRepository;
import com.lhrsite.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhr
 * @create 2018/1/31
 */
@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagRepository repository;


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
}
