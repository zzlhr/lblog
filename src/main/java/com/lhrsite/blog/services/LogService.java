package com.lhrsite.blog.services;

import com.lhrsite.blog.entity.Log;

import java.util.List;

/**
 * 日志服务
 * @author lhr
 * @create 2018/1/26
 */
public interface LogService {

    /**
     * 写日志
     * @param log   日志对象
     */
    void writeLog(Log log);

    /**
     * 写一组日志
     * @param logList   日志组
     */
    void writeLog(List<Log> logList);


    /**
     * 读日志
     * @param page  分页
     * @return      日志集合
     */
    List<Log> readLog(Integer page);

}
