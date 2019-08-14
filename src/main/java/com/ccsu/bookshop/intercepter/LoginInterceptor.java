package com.ccsu.bookshop.intercepter;


import com.ccsu.bookshop.bean.HostHolder;
import com.ccsu.bookshop.bean.LoginTicket;
import com.ccsu.bookshop.bean.User;
import com.ccsu.bookshop.dao.LoginTicketDao;
import com.ccsu.bookshop.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @create 2018-12-08-21:45
 */


public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTicketDao loginTicketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object obj, Exception err)
            throws Exception {

        hostHolder.clear();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object obj, ModelAndView mav) throws Exception {


    }



    //方法请求之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {
        if (hostHolder.getUser() == null) {
            response.sendRedirect("/jsp/user/login.jsp");
            return false;
        }


        return true;

    }
}