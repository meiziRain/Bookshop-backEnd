package com.ccsu.bookshop.service;

import com.ccsu.bookshop.bean.LoginTicket;
import org.apache.ibatis.annotations.Param;

/**
 * @create 2018-12-09-12:07
 */
public interface LoginTicketService {

    int addTicket(LoginTicket loginTicket);
    void updateStatus(String ticket, int status);
    LoginTicket selectByTicket(String ticket);
    String addLoginTicket(int userId);
}
