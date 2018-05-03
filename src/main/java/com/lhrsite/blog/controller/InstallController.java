package com.lhrsite.blog.controller;

import com.lhrsite.blog.code.Encrypt;
import com.lhrsite.blog.consts.UserStatusConst;
import com.lhrsite.blog.consts.UserTypeConst;
import com.lhrsite.blog.entity.User;
import com.lhrsite.blog.entity.Website;
import com.lhrsite.blog.exceptions.EncryptException;
import com.lhrsite.blog.services.UserService;
import com.lhrsite.blog.services.WebsiteService;
import com.lhrsite.blog.vo.AlertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述
 *
 * @author lhr
 * @data 2018/5/3
 * @site https://www.lhrsite.com
 */
@Controller
@RequestMapping("/install")
public class InstallController {

    private final WebsiteService websiteService;
    private final UserService userService;
    @Autowired
    public InstallController(WebsiteService websiteService, UserService userService) {
        this.websiteService = websiteService;
        this.userService = userService;
    }


    @GetMapping({"/", "/index.html"})
    public String index(){
        if (websiteService.isInstall()){
            return "redirect:/";
        }else {
            return "install/index";
        }
    }

    @PostMapping("/init")
    public String init(String host, String name,
                       String keyword, String description,
                       String masterName, String masterEmail){
        Website website = Website.builder()
                .host(host)
                .name(name)
                .keywords(keyword)
                .description(description)
                .masterName(masterName)
                .masterEmail(masterEmail)
                .build();
        System.out.println(website);
        try {
            websiteService.init(website);
        }catch (Exception e){
            return AlertVO.alertAndRunJs("出现异常，请联系作者！", "location.href=goback(-1);");
        }
        return "redirect:user-add.html";
    }


    @GetMapping("/user-add.html")
    public String addUserPage(){
        return "install/add-user";
    }

    @PostMapping("/user-add.html")
    public String addUser(
            String loginName, String username,
            String password, Model model)
            throws EncryptException {
        password = Encrypt.md5AddSalt(password);

        User user = User.builder()
                .loginName(loginName)
                .username(username)
                .password(password)
                .status(UserStatusConst.USE)
                .admin(1)
                .build();

        if (userService.initUser(user) != null){
            return "install/success";
        }else {
            model.addAttribute("msg", "您已经初始化完成，无法再进行初始化操作。");
            return "install/error";
        }
    }

}
