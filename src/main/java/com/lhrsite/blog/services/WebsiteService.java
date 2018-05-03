package com.lhrsite.blog.services;

import com.lhrsite.blog.entity.Website;

/**
 * 描述
 *
 * @author lhr
 * @data 2018/5/3
 * @site https://www.lhrsite.com
 */
public interface WebsiteService {

    boolean isInstall();

    Website getWebsite();

    Website update(Website website);

    Website init(Website website);

}
