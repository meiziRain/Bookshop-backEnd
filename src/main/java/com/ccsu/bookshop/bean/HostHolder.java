package com.ccsu.bookshop.bean;

import org.springframework.stereotype.Component;

/**
 * @create 2018-12-09-18:40
 */

@Component
public class HostHolder {

    //线程本地变量
    private static  ThreadLocal<User> users = new ThreadLocal<>();

    public User  getUser() {
        return users.get();
    }

    public  void setUser(User user) {
       users.set(user);
    }

    public  void clear(){
        users.remove();
    }
}
