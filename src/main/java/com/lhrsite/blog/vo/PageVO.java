package com.lhrsite.blog.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * 分页对象
 * @author lhr
 * @create 2018/1/31
 */
@Data
@Builder
public class PageVO {

    @Tolerate
    public PageVO(){}

    private int totalPages;

    private long totalElements = 0L;

    private int number;

    private int size;

    private int numberOfElements;

}
