//package com.hntxrj.boot.blog.task;
//
//import com.hntxrj.boot.blog.consts.StatisticsConst;
//import com.hntxrj.boot.blog.entity.ArticleStatistics;
//import com.hntxrj.boot.blog.repository.ArticleStatisticsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @author lhr
// * @create 2018/2/7
// */
//@Component
//public class TimeTask {
//
//    private final ArticleStatisticsRepository repository;
//
//    @Autowired
//    public TimeTask(ArticleStatisticsRepository repository) {
//        this.repository = repository;
//    }
//
//    /** 在每个月最后一分钟创建下个月的统计 */
//    @Scheduled(cron = "0 59 23 L * ?")
//    public void createNextArticleStatistics(){
//        ArticleStatistics articleStatistics = new ArticleStatistics();
//        String year = String.valueOf(new Date().getYear());
//        String month = String.valueOf(new Date().getMonth() + 1);
//
//        if ("13".equals(month)){
//            month = "01";
//            year = String.valueOf(Integer.parseInt(year) + 1).substring(2, 4);
//        }
//        if(month.length() == 1){
//            month = "0" + month;
//        }
//        String name = year + "-" + month;
//
//        articleStatistics.setStatisticsName(name);
//        articleStatistics.setStatisticsType(StatisticsConst.MONTHLY_NUMBER_OF_ARTICLES);
//        articleStatistics.setStatisticsValue("0");
//        repository.save(articleStatistics);
//    }
//
//
//
//
//
//}
