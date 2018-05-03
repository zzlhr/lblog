package com.lhrsite.blog.services.impl;

import com.lhrsite.blog.entity.Website;
import com.lhrsite.blog.repository.WebsiteRepository;
import com.lhrsite.blog.services.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @author lhr
 * @data 2018/5/3
 * @site https://www.lhrsite.com
 */
@Service
public class WebsiteServiceImpl implements WebsiteService {

    private final WebsiteRepository websiteRepository;

    @Autowired
    public WebsiteServiceImpl(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }


    @Override
    public boolean isInstall() {
        return websiteRepository.findAll().size() != 0;
    }

    @Override
    public Website getWebsite() {
        return websiteRepository.findById(1).get();
    }

    @Override
    public Website update(Website website) {
        website.setId(1);
        return websiteRepository.save(website);
    }

    @Override
    public Website init(Website website) {
        website.setId(1);
        return websiteRepository.save(website);
    }
}
