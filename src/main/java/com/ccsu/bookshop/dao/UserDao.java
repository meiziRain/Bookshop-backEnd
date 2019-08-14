package com.ccsu.bookshop.dao;

import com.ccsu.bookshop.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User selectByUserId(Integer  userId);
    User selectByUsername(String username);

    int insertUser(User user);

    int updateImage(@Param("url") String url, @Param("uid") int uid);
}
