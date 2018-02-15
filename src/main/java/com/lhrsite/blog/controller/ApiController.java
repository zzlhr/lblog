package com.lhrsite.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhrsite.blog.code.Encrypt;
import com.lhrsite.blog.consts.UserStatusConst;
import com.lhrsite.blog.consts.UserTypeConst;
import com.lhrsite.blog.entity.User;
import com.lhrsite.blog.services.ArticleService;
import com.lhrsite.blog.services.UserService;
import com.lhrsite.blog.util.HttpClientUtil;
import com.lhrsite.blog.vo.AlertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lhr
 * @create 2018/2/10
 */
@Controller
@RequestMapping("/api")
public class ApiController {


    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;


    private final UserService userService;

    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/login.do")
    public String login(String code, HttpServletRequest request){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.setUrl("https://github.com/login/oauth/access_token");
        Map param = new HashMap<>(3);
        System.out.println(clientId);
        System.out.println(clientSecret);
        param.put("client_id", clientId);
        param.put("client_secret", clientSecret);
        param.put("code", code);
        httpClientUtil.setParams(param);
        String accessToken = (String) httpClientUtil.send();
        httpClientUtil = new HttpClientUtil();
        httpClientUtil.setUrl("https://api.github.com/user?" + accessToken);
        String result = (String) httpClientUtil.send();
        JSONObject userResult = JSONObject.parseObject(result);
        System.out.println(userResult);
        User user = userService.getUserByLoginName(userResult.getString("login"));
        String token = Encrypt.base64Encode(UUID.randomUUID().toString().getBytes());
        if (user == null) {
            user = new User();
            user.setId(userResult.getInteger("id"));
            user.setAdmin(0);
            user.setUsername(userResult.getString("login"));
            user.setHeaderUrl("https://avatars0.githubusercontent.com/u/" + user.getId());
            user.setStatus(UserStatusConst.USE);
            user.setType(UserTypeConst.GITHUB);
            user.setToken(token);
            user.setLoginName(userResult.getString("id"));
            user = userService.saveUser(user);
        }else {
            user.setToken(token);
            userService.saveUser(user);
        }
        request.getSession().setAttribute("user", user);
        return AlertVO.alertAndRunJs("登录成功！",
                "location.href='../article.html?id='+sessionStorage.getItem('article_id')");
    }


}
