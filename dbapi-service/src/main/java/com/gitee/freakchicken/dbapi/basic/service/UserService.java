package com.gitee.freakchicken.dbapi.basic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.gitee.freakchicken.dbapi.basic.dao.UserMapper;
import com.gitee.freakchicken.dbapi.basic.domain.User;
import com.gitee.freakchicken.dbapi.basic.util.MD5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@DS("meta-db")
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUser(String username, String password) {
        
        User user = userMapper.login(username, MD5.encodeByMd5(password));
        return user;
    }

    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Transactional
    public void resetPassword(String password) {
        userMapper.updatePassword(MD5.encodeByMd5(password));
    }
}
