package com.ccsu.bookshop.service.impl;

import com.ccsu.bookshop.bean.LoginTicket;
import com.ccsu.bookshop.dao.LoginTicketDao;
import com.ccsu.bookshop.service.LoginTicketService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @create 2018-12-09-12:08
 */
@Service
public class LoginTicketServiceImpl implements LoginTicketService {



     @Autowired
    private LoginTicketDao loginTicketDao;
    @Override
    public int addTicket(LoginTicket loginTicket) {
        return 0;
    }

    @Override
    public void updateStatus(String ticket, int status) {
        loginTicketDao.updateStatus(ticket,status);

    }

    @Override
    public LoginTicket selectByTicket(String ticket) {



        return null;
    }


    public   String addLoginTicket(int userId){
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date= new Date();
        date.setTime(date.getTime()+3600*24*50);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        loginTicketDao.addTicket(ticket);
        return  ticket.getTicket();
    }

    @Test
    public  void t(){
        Date date= new Date();
        date.setTime(date.getTime()+3600*24*500);
        System.out.println(date);
    }

}
