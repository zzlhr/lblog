package com.lhrsite.blog.controller;

import com.lhrsite.blog.entity.Article;
import com.lhrsite.blog.entity.FriendLink;
import com.lhrsite.blog.entity.User;
import com.lhrsite.blog.enums.ResultCodeEnums;
import com.lhrsite.blog.repository.FriendLinkRepository;
import com.lhrsite.blog.services.ArticleService;
import com.lhrsite.blog.services.UserService;
import com.lhrsite.blog.vo.AlertVO;
import com.lhrsite.blog.vo.ArticleVO;
import com.lhrsite.blog.vo.PageContentVO;
import com.lhrsite.blog.vo.UploadVO;
import com.lhrsite.blog.request.Ip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 后台管理
 * @author lhr
 * @create 2018/2/1
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {


    private final ArticleService articleService;

    private final UserService userService;

    private final Environment env;

    private final FriendLinkRepository friendLinkRepository;

    @Autowired
    public AdminController(ArticleService articleService, UserService userService, Environment env, FriendLinkRepository friendLinkRepository) {
        this.articleService = articleService;
        this.userService = userService;
        this.friendLinkRepository = friendLinkRepository;
        this.env = env;
    }

    @RequestMapping({"/index.html", "/"})
    public String index(Model model){
        model.addAttribute("title", "lhr的博客后台管理系统");
        return "admin/index";
    }


    @GetMapping("/login.html")
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login.html")
    public String login(String username, String password, HttpServletRequest request){
        User user = userService.login(username, password);
        if (user == null){
            return "redirect:login.html";
        }
        request.getSession().setAttribute("userToken", user.getToken());
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("userName", user.getUsername());
        request.getSession().setAttribute("admin", user);
        return "redirect:index.html";
    }

    @GetMapping("/article-update.html")
    public String updateArticle(Integer id, HttpServletRequest request, Model model){
        ArticleVO article = articleService.getArticleInfo(Ip.getIpAddress(request), id);
        model.addAttribute("title", "编辑文章");
        System.out.println(article);
        model.addAttribute("article", article);

        return "admin/article-add";

    }


    @RequestMapping("/articles.html")
    public String articles(@RequestParam(defaultValue = "1") Integer page,
                           Model model, HttpServletRequest request){
        model.addAttribute("title", "文章管理");

        PageRequest pageRequest = new PageRequest(page - 1, 10,
                new Sort(Sort.Direction.DESC, "id"));
        PageContentVO<ArticleVO> articleVOPageContentVO =
                articleService.getArticleList(Ip.getIpAddress(request), pageRequest);

        model.addAttribute("articles", articleVOPageContentVO);
        return "admin/articles";
    }


    @GetMapping("/friendlink.html")
    public String friendLink(Model model){
        model.addAttribute("title", "友链管理");
        model.addAttribute("friendLinks", friendLinkRepository.findAll());
        return "admin/friendlink";
    }
    @ResponseBody
    @PostMapping("/friendlink-edit.html")
    public String addFriendLink(String url, String value){
        FriendLink friendLink = friendLinkRepository.findByUrl(url);
        if(friendLink == null){
            friendLink = new FriendLink();
            friendLink.setUrl(url);
            friendLink.setValue(value);
        }else {
            friendLink.setValue(value);
        }
        friendLinkRepository.save(friendLink);
        return AlertVO.alert(0, "编辑友链成功!");
    }

    @ResponseBody
    @GetMapping("/friendlink-delect.html")
    public String delectFriendLink(Integer id){
        friendLinkRepository.delete(id);
        return AlertVO.alert(0, "删除友链成功!");
    }

    @GetMapping("/article-add.html")
    public String articleAdd(Model model){
        model.addAttribute("title", "编辑文章");
        return "admin/article-add";
    }

    @ResponseBody
    @PostMapping("/article-add.html")
    public String addArticle(String title, String tag, String describe,
                             @RequestParam(defaultValue = "0") Integer status,
                             @RequestParam(name = "editormd-markdown-doc") String mdContent,
                             @RequestParam(name = "editormd-html-code") String htmlContent,
                             HttpServletRequest request){
        Article article = new Article();
        article.setArticleStatus(status);
        article.setArticleContentHtml(htmlContent);
        article.setArticleContentMd(mdContent);
        article.setArticleTitle(title);
        article.setArticleDescribe(describe);

        String[] tagContents = tag.split(",");
        if (tagContents.length == 0 && !"".equals(tag)){
            tagContents = new String[]{tag};
        }
        User user = (User) request.getSession().getAttribute("admin");
        article.setArticleAuthor(user);
        articleService.addArticle(Ip.getIpAddress(request),
                article, tagContents, user);

        return AlertVO.alert(0, "添加文章成功!");
    }


    @ResponseBody
    @PostMapping("/article-update.html")
    public String updateArticle(Integer id, String title, String tag, String describe,
                             @RequestParam(defaultValue = "0") Integer status,
                             @RequestParam(name = "editormd-markdown-doc") String mdContent,
                             @RequestParam(name = "editormd-html-code") String htmlContent,
                             HttpServletRequest request){
        ArticleVO articleVO = articleService.getArticleInfo(Ip.getIpAddress(request), id);
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        article.setArticleStatus(status);
        article.setArticleContentHtml(htmlContent);
        article.setArticleContentMd(mdContent);
        article.setArticleTitle(title);
        article.setArticleDescribe(describe);

        String[] tagContents = tag.split(",");

        User user = (User) request.getSession().getAttribute("admin");
        article.setArticleAuthor(user);
        articleService.updateArticle(Ip.getIpAddress(request),
                article, tagContents, user);

        return AlertVO.alert(0, "修改文章成功!");
    }

    /**
     * 图片文件上传
     */
    @ResponseBody
    @RequestMapping(value = "/photoUpload.do", method = RequestMethod.POST)
    public UploadVO photoUpload(MultipartFile file,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                HttpSession session)
            throws IllegalStateException, IOException {
        UploadVO resultData = new UploadVO();
        // 判断用户是否登录
        /*User user=(User) session.getAttribute("user");
        if (user==null) {
            resultData.setCode(40029);
            resultData.setMsg("用户未登录");
            return resultData;
        }*/

        // 判断上传的文件是否为空
        if (file!=null) {
            // 文件路径
            String path;
            // 文件类型
            String type;
            // 文件原名称
            String fileName = file.getOriginalFilename();
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.contains(".") ?
                    fileName.substring(fileName.lastIndexOf(".") + 1,
                            fileName.length()) : null;
            // 判断文件类型是否为空
            if (type!=null) {
                if ("GIF".equals(type.toUpperCase()) ||
                        "PNG".equals(type.toUpperCase()) ||
                        "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath=request.getSession()
                            .getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName =
                            String.valueOf(System.currentTimeMillis())
                                    + fileName;
                    // 设置存放图片文件的路径
                    path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
                    System.out.println("存放图片文件的路径:" + path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
                }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            }else {
                System.out.println("文件类型为空");
                return null;
            }
        }else {
            System.out.println("没有找到相对应的文件");
            return null;
        }
        return resultData;
    }



    @RequestMapping(value="/uploadBase64.do",method=RequestMethod.POST)
    @ResponseBody
    public UploadVO base64UpLoad(
            @RequestParam(name = "base") String base64Data,
            HttpServletRequest request,
            HttpServletResponse response){
        UploadVO result = new UploadVO();
        try{
            log.debug("上传文件的数据："+base64Data);
            String dataPrix;
            String data;

            log.debug("对数据进行判断");
            if(base64Data == null || "".equals(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    throw new Exception("上传失败，数据不合法");
                }
            }

            log.debug("对数据进行解析，获取文件名和流数据");
            String suffix = "";
            //data:image/jpeg;base64,base64编码的jpeg图片数据
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
                suffix = ".jpg";
                //data:image/x-icon;base64,base64编码的icon图片数据
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
                suffix = ".ico";
                //data:image/gif;base64,base64编码的gif图片数据
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
                suffix = ".gif";
                //data:image/png;base64,base64编码的png图片数据
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){
                suffix = ".png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            String tempFileName = System.currentTimeMillis() + suffix;
            log.debug("生成文件名为："+tempFileName);

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            try{
                //使用apache提供的工具类操作流
                FileOutputStream fos = new FileOutputStream(
                        env.getProperty("blog.upload.image.path")
                        + tempFileName);
                fos.write(bs);
                fos.close();
            }catch(Exception ee){
                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
            }
            result.setCode(ResultCodeEnums.UPLOAD_PICTURE_SUCCESS.getCode());
            result.setMsg(ResultCodeEnums.UPLOAD_PICTURE_SUCCESS.getMsg());
            result.setPath("/images/"+tempFileName);
            log.debug("上传成功");
        }catch (Exception e) {
            result.setCode(ResultCodeEnums.UPLOAD_PICTURE_ERROR.getCode());
            result.setMsg(ResultCodeEnums.UPLOAD_PICTURE_ERROR.getMsg());
        }
        return result;
    }




}
