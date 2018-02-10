package com.lhrsite.blog.controller;

import com.lhrsite.blog.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author lhr
 * @create 2018/2/2
 */
@Controller
public class PublicController {

    @Autowired
    private Environment env;

    @RequestMapping("/images/{file}.{type}")
    public void image(@PathVariable(name="file") String file,
                        @PathVariable(name="type") String type,
                        HttpServletResponse response){

        try {
            response.setContentType("image/*");
            OutputStream stream = response.getOutputStream();
            assert stream != null;
            stream.write(FileUtils.toByteArray(env.getProperty("blog.upload.image.path")
                    + file + "." + type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
