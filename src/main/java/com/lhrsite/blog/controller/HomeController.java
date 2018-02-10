package com.lhrsite.blog.controller;

import com.lhrsite.blog.repository.FriendLinkRepository;
import com.lhrsite.blog.services.ArticleService;
import com.lhrsite.blog.services.TagService;
import com.lhrsite.blog.vo.ArticleVO;
import com.lhrsite.blog.vo.PageContentVO;
import com.lhrsite.blog.request.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("article", articleVO);
        init(model);
        model.addAttribute("path", "article");
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
        if (keyword == null || "".equals(keyword)){
            //
        }

        PageRequest pageRequest =
                new PageRequest(page - 1, 10,
                        new Sort(Sort.Direction.DESC, "id"));

        PageContentVO<ArticleVO> articleVOS =
                articleService.getArticleList(keyword, Ip.getIpAddress(request), pageRequest);
        model.addAttribute("articles", articleVOS);
        model.addAttribute("path", "search");
        init(model);
        return "articles";



    }



    public Model init(Model model){
        model.addAttribute("topTenTags", tagService.getTopTenTag());
        model.addAttribute("friendLinks", friendLinkRepository.findAll());
        return model;
    }


}
