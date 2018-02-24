package com.lhrsite.blog.code;

import com.lhrsite.blog.exceptions.EncryptException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lhr
 * @create 2018/2/24
 */
public class EncryptTest {

    @Test
    public void md5() {
    }

    @Test
    public void md5AddSalt() throws EncryptException {
        System.out.println(Encrypt.md5AddSalt("Liu19990114"));
    }

    @Test
    public void base64Encode() {
    }

    @Test
    public void base64Decode() {
    }
}