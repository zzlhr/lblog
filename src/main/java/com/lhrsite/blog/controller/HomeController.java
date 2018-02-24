package com.lhrsite.blog.controller;

import com.lhrsite.blog.entity.ArticleComment;
import com.lhrsite.blog.entity.User;
import com.lhrsite.blog.repository.FriendLinkRepository;
import com.lhrsite.blog.services.ArticleService;
import com.lhrsite.blog.services.TagService;
import com.lhrsite.blog.vo.AlertVO;
import com.lhrsite.blog.vo.ArticleVO;
import com.lhrsite.blog.vo.PageContentVO;
import com.lhrsite.blog.request.Ip;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Value("${blog.domain}")
    private String domain;

    @Autowired
    public HomeController(ArticleService articleService, TagService tagService, FriendLinkRepository friendLinkRepository) {
        this.articleService = articleService;
        this.tagService = tagService;
        this.friendLinkRepository = friendLinkRepository;
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

        ArticleVO articleVO =
                articleService.getArticleInfo(Ip.getIpAddress(request), id);


        // 获取文章评论
        List<ArticleComment> articleComments = articleService.getArticleComments(id,
                new PageRequest(0, 10,
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
        PageRequest pageRequest = new PageRequest(page - 1, 10,
                new Sort(Sort.Direction.DESC, "updateTime"));

        PageContentVO<ArticleVO> articles =
                articleService.getArticleList(
                        keyword, Ip.getIpAddress(request), pageRequest);
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
            PageRequest pageRequest =
                    new PageRequest(page - 1, 10,
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
    public String sendComment(Integer articleId, String comment,
                              HttpServletRequest request){
        User user = (User) request
                .getSession()
                .getAttribute("user");

        if (user == null){
            return AlertVO.alert(-1, "请先登录");
        }

        ArticleComment articleComment = new ArticleComment();

        if (articleId == null || "".equals(articleId)){
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


        if (articleId == null || "".equals(articleId)){
            code = -1;
            msg = "文章id不能为空";
        }
        articleComment.setArticleId(articleId);

        if (userId == null){
            code = -1;
            msg = "用户id为空";
        }
        User userEntity = new User();
        user.setId(userId);
        articleComment.setUser(user);

        articleComment.setCommentIp(Ip.getIpAddress(request));

        if (code == -1 || !articleService.sendComment(articleComment)){
            code = -1;
            msg = "发布评论失败！";
        }
        return AlertVO.alertAndSuccessGoToPath(code,
                msg,
                "article.html?id=" + articleId);
    }


    public Model init(Model model){
        model.addAttribute("topTenTags", tagService.getTopTenTag());
        model.addAttribute("friendLinks", friendLinkRepository.findAll());
        return model;
    }


}
