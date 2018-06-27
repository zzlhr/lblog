package com.lhrsite.blog.util;

import com.lhrsite.blog.code.Encrypt;
import com.lhrsite.blog.exceptions.EncryptException;

import java.util.UUID;

public class LoginUtil {


    public static String makeToken() throws EncryptException {
        return Encrypt.md5AddSalt(UUID.randomUUID().toString());
    }

}
