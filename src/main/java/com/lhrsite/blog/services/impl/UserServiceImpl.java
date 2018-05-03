package com.lhrsite.blog.services.impl;

import com.lhrsite.blog.entity.User;
import com.lhrsite.blog.repository.UserRepository;
import com.lhrsite.blog.services.UserService;
import com.lhrsite.blog.code.Encrypt;
import com.lhrsite.blog.exceptions.EncryptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author lhr
 * @create 2018/2/3
 */
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User login(String userName, String password) {
        User user = repository.findByUsername(userName);

        if(user == null){
            return null;
        }
        try {
            password = Encrypt.md5AddSalt(password);
            System.out.println(password);
            System.out.println(user);
            if (password.equals(user.getPassword())){
                String token = Encrypt.md5AddSalt(UUID.randomUUID().toString());
                user.setToken(token);
                repository.save(user);
                return user;
            }
        } catch (EncryptException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean isLogin(String token, String userName) {
        if (token == null || userName == null){
            return false;
        }
        User user = repository.findByUsername(userName);
        return user.getToken().equals(token);

    }

    @Override
    public User getOne(String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    public User getOne(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean updatePassword(Integer userId, String newPassword) {
        User user = repository.findById(userId).get();
        user.setPassword(newPassword);
        repository.save(user);
        return true;
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User initUser(User user) {
        if (repository.findAll().size() != 0){
            return null;
        }
        return repository.save(user);
    }


    @Override
    public User getUserByLoginName(String loginName) {
        return repository.findByLoginName(loginName);
    }

}
