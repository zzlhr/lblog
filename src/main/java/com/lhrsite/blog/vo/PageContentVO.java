package com.lhrsite.blog.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhr
 * @create 2018/1/31
 */
@Data
public class PageContentVO<T> extends PageVO {

    private List<T> contents = new ArrayList<>();

}
