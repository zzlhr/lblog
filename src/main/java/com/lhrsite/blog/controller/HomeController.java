package com.lhrsite.blog.controller;

import com.lhrsite.blog.entity.*;
import com.lhrsite.blog.repository.FriendLinkRepository;
import com.lhrsite.blog.services.ArticlePlaceService;
import com.lhrsite.blog.services.ArticleService;
import com.lhrsite.blog.services.TagService;
import com.lhrsite.blog.services.WebsiteService;
import com.lhrsite.blog.vo.AlertVO;
import com.lhrsite.blog.vo.ArticleVO;
import com.lhrsite.blog.vo.PageContentVO;
import com.lhrsite.blog.request.Ip;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lhr
 * @create 2018/1/27
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final ArticleService articleService;

    private final TagService tagService;

    private final FriendLinkRepository friendLinkRepository;
    private final ArticlePlaceService articlePlaceService;
    private final WebsiteService websiteService;
    @Value("${blog.domain}")
    private String domain;

    @PersistenceContext
    private final EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    @Autowired
    public HomeController(ArticleService articleService, TagService tagService, FriendLinkRepository friendLinkRepository, ArticlePlaceService articlePlaceService, WebsiteService websiteService, EntityManager entityManager) {
        this.articleService = articleService;
        this.tagService = tagService;
        this.friendLinkRepository = friendLinkRepository;
        this.articlePlaceService = articlePlaceService;
        this.websiteService = websiteService;
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @RequestMapping({"/index.html", "/"})
    public String index(Model model, HttpServletRequest request){

        String ip = Ip.getIpAddress(request);

        List<ArticleVO> articleList = articleService.getIndexArticle(ip);

        init(model);
        model.addAttribute("path", "index");
        model.addAttribute("articles", articleList);
        return "index";
    }

    @GetMapping("/article.html")
    public String article(Integer id, HttpServletRequest request, Model model){

        // 添加点击
        articleService.upInfo(0, id);

        ArticleVO articleVO =
                articleService.getArticleInfo(Ip.getIpAddress(request), id);



        //todo 更多评论功能
        // 获取文章评论
        List<ArticleComment> articleComments = articleService.getArticleComments(id,
                PageRequest.of(0, 10,
                        new Sort(Sort.Direction.DESC, "createTime")))
                .getContent();

        User user = (User) request
                .getSession()
                .getAttribute("user");

        model.addAttribute("article", articleVO);

        init(model);
        model.addAttribute("user", user);
        model.addAttribute("domain", domain);
        model.addAttribute("path", "article");

        model.addAttribute("comments", articleComments);

        return "article";
    }

    @GetMapping("/articles.html")
    public String articles(@RequestParam(defaultValue = "") String keyword,
                           @RequestParam(defaultValue = "1") Integer page,
                           HttpServletRequest request, Model model){
        PageRequest pageRequest = PageRequest.of(page - 1, 10,
                new Sort(Sort.Direction.DESC, "updateTime"));

        PageContentVO<ArticleVO> articles =
                articleService.getArticleList(
                        keyword, Ip.getIpAddress(request), pageRequest);
//        System.out.println(articles);

        model.addAttribute("articles", articles);
        model.addAttribute("path", "articles");
        init(model);
        return "articles";
    }

    @GetMapping("/about.html")
    public String about(Model model){
        model.addAttribute("path", "about");
        init(model);
        return "about";
    }

    /** other tools */



    @GetMapping("/search.html")
    public String search(String keyword,
                         @RequestParam(defaultValue = "1") Integer page,
                         HttpServletRequest request,Model model){
        PageContentVO<ArticleVO> articleVOS;
        if (keyword == null || "".equals(keyword)){
            //
            articleVOS = new PageContentVO<>();
        }else{
            if (keyword.equals("周乐娟")) {
                return "redirect:goto.html?u=http://love.lhrsite.com/";
            }
            PageRequest pageRequest =
                    PageRequest.of(page - 1, 10,
                            new Sort(Sort.Direction.DESC, "id"));

            articleVOS= articleService.
                    getArticleList(keyword, Ip.getIpAddress(request), pageRequest);
        }


        model.addAttribute("articles", articleVOS);
        model.addAttribute("path", "search");
        init(model);
        return "articles";
    }

    @ResponseBody
    @PostMapping("/send_comment")
    public String sendComment(
            @RequestParam(defaultValue = "0") Integer articleId,
            String comment, HttpServletRequest request){

        User user = (User) request
                .getSession()
                .getAttribute("user");

        if (user == null){
            return AlertVO.alert(-1, "请先登录");
        }

        ArticleComment articleComment = new ArticleComment();

        if (articleId == 0){
            return AlertVO.alert(-1, "文章id不能为空");
        }


        int code = 0;

        String msg = "发布评论成功！";


        Integer userId = user.getId();

        if (comment == null || "".equals(comment)){
            code = -1;
            msg = "评论内容不能为空";
        }
        articleComment.setCommentContent(comment);
        Article article = new Article();
        article.setId(articleId);
        articleComment.setArticle(article);

        if (userId == null){
            code = -1;
            msg = "用户id为空";
        }
        user.setId(userId);
        articleComment.setUser(user);

        articleComment.setCommentIp(Ip.getIpAddress(request));

        if (code == -1 || !articleService.sendComment(articleComment)){
            code = -1;
            msg = "发布评论失败！";
        }else{
            // 添加留言
            articleService.upInfo(3, articleId);
        }
        return AlertVO.alertAndSuccessGoToPath(code,
                msg,
                "article.html?id=" + articleId);
    }


    @GetMapping("/tag.html")
    public String tag(String tag,
                      @RequestParam(defaultValue = "1") Integer page,
                      HttpServletRequest request, Model model){
        PageRequest pageRequest =
                PageRequest.of(page - 1, 10,
                        Sort.by(Sort.Direction.DESC,
                                "articleId"));

        Page<ArticleTag> articleTags =
                tagService.findArticleTag(tag, pageRequest);

        model.addAttribute("page", articleTags);
        List<Integer> articleIds = new ArrayList<>();

        for (ArticleTag articleTag : articleTags.getContent()){
            articleIds.add(articleTag.getArticleId());
        }
        model.addAttribute("tag",tag);
        model.addAttribute("path", "tag");
        model.addAttribute("articles", articleService.getArticleByIds(articleIds));
        init(model);
        return "tag";
    }


    @GetMapping("/place.html")
    public String place(String value,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model){
        System.out.println(value);
        String[] values = value.split("/");

        PageContentVO<ArticleVO> articlePageContentVO =
                articleService.findArticleByYearAndMonth(values[0], values[1],
                PageRequest.of(page - 1,10));

        model.addAttribute("articles", articlePageContentVO);
        init(model);
        model.addAttribute("path", "place");

        return "articles";


    }

    @ResponseBody
    @GetMapping("/goto.html")
    public String go(String u){
        return "<script>location.href='"+ u +"'</script>";
    }

    public Model init(Model model){
        Website website = websiteService.getWebsite();
        System.out.println(website);
        model.addAttribute("topTenTags", tagService.getTopTenTag());
        model.addAttribute("friendLinks", friendLinkRepository.findAll());
        model.addAttribute("places", articlePlaceService.Top10Place());
        model.addAttribute("website", website);
        return model;
    }



}
