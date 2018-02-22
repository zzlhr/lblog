package com.lhrsite.blog.services.impl;

import com.lhrsite.blog.consts.ArticleStatusConst;
import com.lhrsite.blog.consts.CommentStatusConst;
import com.lhrsite.blog.entity.*;
import com.lhrsite.blog.repository.ArticleCommentRepository;
import com.lhrsite.blog.repository.ArticleRepository;
import com.lhrsite.blog.repository.ArticleTagRepository;
import com.lhrsite.blog.services.ArticleService;
import com.lhrsite.blog.services.LogService;
import com.lhrsite.blog.services.TagService;
import com.lhrsite.blog.vo.ArticleVO;
import com.lhrsite.blog.vo.PageContentVO;
import com.lhrsite.blog.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章服务
 * @author lhr
 * @create 2018/1/26
 */
@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleRepository repository;

    private final LogService log;

    private final TagService tagService;

    private final ArticleCommentRepository commentRepository;

    private final ArticleTagRepository tagRepository;



    @Autowired
    public ArticleServiceImpl(ArticleRepository repository, LogService log, TagService tagService, ArticleTagRepository tagRepository, ArticleCommentRepository commentRepository) {
        this.repository = repository;
        this.log = log;
        this.tagService = tagService;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<ArticleVO> getIndexArticle(String ip) {

        // 查询前五条文章
        PageRequest pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id"));

        log.writeLog(new Log(ip, "获取首页文章"));

        List<Article> articles = repository
                .findAllByArticleStatus(ArticleStatusConst.SHOW, pageRequest)
                .getContent();



        return getArticleVOS(articles);
    }

    @Override
    public PageContentVO<ArticleVO> getArticleList(String ip, PageRequest page) {
        return getArticleList(ip, page, ArticleStatusConst.SHOW);

    }

    @Override
    public PageContentVO<ArticleVO> getArticleList(String keyword,
                                                   String ip, PageRequest page) {
        log.writeLog(new Log(ip, "获取文章列表第" + page.getPageNumber() + 1 + "页"));

        if ("".equals(keyword)){
            return getArticleList(ip, page);
        }
        Page<Article> articles = repository
                .findAllByArticleStatusAndArticleTitleLike(ArticleStatusConst.SHOW,
                        keyword + "%", page);

        PageVO pageVO = new PageVO();

        BeanUtils.copyProperties(articles, pageVO);

        List<ArticleVO> articleVOS = getArticleVOS(articles.getContent());

        PageContentVO<ArticleVO> articleVOPageContentVO = new PageContentVO<>();

        BeanUtils.copyProperties(pageVO, articleVOPageContentVO);

        articleVOPageContentVO.setContents(articleVOS);

        return articleVOPageContentVO;

    }

    @Override
    public PageContentVO<ArticleVO> getArticleList(String ip, PageRequest page, Integer status) {
        // 获取文章对象根据分页查询并且状态位show的

        log.writeLog(new Log(ip, "获取文章列表第" + page.getPageNumber() + 1 + "页"));

        Page<Article> articles = null;

        if(status != null){
            articles = repository
                    .findAllByArticleStatus(status, page);
        }else {
            articles = repository.findAll(page);
        }

        PageVO pageVO = new PageVO();

        BeanUtils.copyProperties(articles, pageVO);

        List<ArticleVO> articleVOS = getArticleVOS(articles.getContent());

        PageContentVO<ArticleVO> articleVOPageContentVO = new PageContentVO<>();

        BeanUtils.copyProperties(pageVO, articleVOPageContentVO);

        articleVOPageContentVO.setContents(articleVOS);

        return articleVOPageContentVO;
    }

    @Override
    public ArticleVO getArticleInfo(String ip, int id) {

        // 查询文章
        Article article = repository.findById(id);

        log.writeLog(new Log(ip, "获取文章详情id=" + id));

        // 判断状态是否为show
        if (article.getArticleStatus().equals(ArticleStatusConst.SHOW)){

            return getArticleVO(article);
        }

        // 不为show 就返回null
        return null;
    }

    @Override
    public ArticleVO updateArticleStatus(String ip, Integer status, Integer articleId, User user) {

        // 更新文章状态

        // 查询文章
        Article article = repository.findOne(articleId);

        article.setArticleStatus(status);

        log.writeLog(new Log(user.getUsername(),
                user.getId(), ip,
                "修改文章状态id=" + article.getId()));


        Article result = repository.save(article);


        return getArticleVO(result);
    }

    @Override
    public ArticleVO updateArticle(String ip, Article article, String[] tagContents, User user) {

        // 更新文章

        log.writeLog(new Log(user.getUsername(),
                user.getId(), ip, "修改文章id=" + article.getId()));

        Article result = repository.save(article);

        ArticleVO articleVO = new ArticleVO();

        List<ArticleTag> articleTags = new ArrayList<>();


        for (String tagContent : tagContents){
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(result.getId());
            articleTag.setTagContent(tagContent);
            if (!"".equals(tagContent)){
                articleTags.add(articleTag);
            }
        }

        List<ArticleTag> articleTagsOld = tagRepository.findAllByArticleId(article.getId());


        for (ArticleTag tag : articleTags){
            boolean addtime = true;
            for (ArticleTag tag1 : articleTagsOld){
                if (tag.equals(tag1)){
                    addtime = false;
                    break;
                }
            }
            if (!addtime){
                tagService.addOneTime(tag.getTagContent());
            }
        }


        tagRepository.delete(articleTagsOld);
        tagRepository.save(articleTags);

        BeanUtils.copyProperties(result, articleVO);

        return articleVO;
    }

    @Override
    public ArticleVO delectArticle(String ip, Article article, User user) {


        log.writeLog(new Log(user.getUsername(),
                user.getId(), ip, "删除文章id=" + article.getId()));

        article.setArticleStatus(ArticleStatusConst.DELECT);


        Article result = repository.save(article);


        return getArticleVO(result);

    }

    @Override
    public ArticleVO addArticle(String ip, Article article, String[] tagContents, User user) {

        log.writeLog(new Log(user.getUsername(),
                user.getId(), ip, "添加文章id=" + article.getId()));

        Article result = repository.save(article);

        List<ArticleTag> articleTags = new ArrayList<>();
        for (String tagContent : tagContents){
            tagService.addOneTime(tagContent);
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(result.getId());
            articleTag.setTagContent(tagContent);
            articleTags.add(articleTag);
        }
        tagRepository.save(articleTags);


        return getArticleVO(result);
    }

    @Override
    public boolean sendComment(ArticleComment articleComment) {

        System.out.println(articleComment);
        // todo: 添加过滤敏感词
        return commentRepository.save(articleComment) != null;

    }

    @Override
    public Page<ArticleComment> getArticleComments(Integer articleId, PageRequest pageRequest) {
        return commentRepository.findByArticleIdAndCommentStatus(articleId,
                CommentStatusConst.SHOW, pageRequest);
    }


    private List<ArticleVO> getArticleVOS(List<Article> articles){
        // 存放所有的文章id值
        List<Integer> articleIds = new ArrayList<>();

        // 将所有的文章id值都整理到一个list中作为下步查询的条件
        for (Article article : articles){
            articleIds.add(article.getId());
        }

        // 根据文章id查询文章标签
        List<ArticleTag> tags = tagRepository.findAllByArticleIdIsIn(articleIds);

        // 拼装文章视图对象
        List<ArticleVO> result = new ArrayList<>();
        for (Article article : articles){
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article, articleVO);
            for (ArticleTag tag : tags){
                if (tag.getArticleId().equals(article.getId())){
                    articleVO.getTagList().add(tag);
                }
            }
            result.add(articleVO);
        }
        return result;
    }

    private ArticleVO getArticleVO(Article article){

        // 根据文章id查询文章标签
        List<ArticleTag> tags = tagRepository.findAllByArticleId(article.getId());

        // 拼装文章视图对象
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        articleVO.setTagList(tags);
        return articleVO;
    }

}
