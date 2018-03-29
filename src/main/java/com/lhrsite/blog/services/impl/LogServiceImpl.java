package com.lhrsite.blog.services.impl;

import com.lhrsite.blog.entity.Log;
import com.lhrsite.blog.repository.LogRepository;
import com.lhrsite.blog.services.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhr
 * @create 2018/1/27
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    private final LogRepository repository;

    @Autowired
    public LogServiceImpl(LogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void writeLog(Log l) {
        log.info("【"+l.getOperation()+"】user={}, ip={}",
                l.getUserName(), l.getId());

        repository.save(l);
    }

    @Override
    public void writeLog(List<Log> logList) {

        for (Log l : logList){
            log.info("【"+l.getOperation()+"】user={}, ip={}",
                    l.getUserName(), l.getId());
        }

        repository.saveAll(logList);

    }

    @Override
    public List<Log> readLog(Integer page) {


        PageRequest pageRequest
                = new PageRequest(page - 1, 10,
                new Sort(Sort.Direction.DESC, "id"));


        return repository.findAll(pageRequest).getContent();

    }
}
