package com.lhrsite.blog.services;

import com.lhrsite.blog.entity.Tag;

import java.util.List;

/**
 * @author lhr
 * @create 2018/1/31
 */
public interface TagService {

    /**
     * 查看前十个标签，按照次数排序。
     * @return  标签集合
     */
    List<Tag> getTopTenTag();

    /**
     * 查看全部标签，按次数排序
     * @return  标签集合
     */
    List<Tag> getAll();

    /**
     * 添加一次标签
     * @param tagContent    标签内容
     */
    Integer addOneTime(String tagContent);




}
