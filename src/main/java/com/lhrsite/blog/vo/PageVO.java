package com.lhrsite.blog.vo;

import lombok.Data;

/**
 * 分页对象
 * @author lhr
 * @create 2018/1/31
 */
@Data
public class PageVO {

    private int totalPages;

    private int totalElements = 0;

    private int number;

    private int size;

    private int numberOfElements;

}
