package com.lhrsite.blog.controller;

import com.lhrsite.blog.file.FileUtils;
import com.lhrsite.blog.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 公共控制器
 * @author lhr
 * @create 2018/2/2
 */
@Controller
public class PublicController {

    private final Environment env;

    @Autowired
    public PublicController(Environment env) {
        this.env = env;
    }

    @RequestMapping("/images/{file}.{type}")
    public void image(@PathVariable(name="file") String file,
                        @PathVariable(name="type") String type,
                        HttpServletResponse response) throws IOException {

        try {
            String fileName = env.getProperty("blog.upload.image.path") +
                    file + "." + type;
            ResponseUtil.responseImage(response, FileUtils
                    .toByteArray(fileName));
        } catch (IOException e) {
            /*==============================*/
            /*
                处理找不到图片后返回默认图片问题
                @time 2018年04月11日11:25:23
                @author lhr
             */
            /*==============================*/
            File defaultImage = ResourceUtils
                    .getFile("classpath:static/images/noimage.png");
            ResponseUtil.responseImage(response,
                    FileUtils.toByteArray(defaultImage.getPath()));
        }
    }
}
