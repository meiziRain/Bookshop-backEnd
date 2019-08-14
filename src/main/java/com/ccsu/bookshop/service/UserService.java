package com.ccsu.bookshop.service;

import com.ccsu.bookshop.bean.User;

public interface  UserService {

    User selectByUserId(Integer userId);
    User selectByUsername(String username);
    int insertUser(User user);


    //上传图片
    int updateImage(String url, int uid);
}
