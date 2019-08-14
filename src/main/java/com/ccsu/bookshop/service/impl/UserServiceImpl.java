package com.ccsu.bookshop.service.impl;

import com.ccsu.bookshop.bean.User;
import com.ccsu.bookshop.dao.UserDao;
import com.ccsu.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectByUserId(Integer userId) {
        return userDao.selectByUserId(userId);
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int updateImage(String url, int uid) {
        return userDao.updateImage(url,uid);
    }
}
