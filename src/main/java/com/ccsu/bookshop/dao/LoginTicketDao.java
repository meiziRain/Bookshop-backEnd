package com.ccsu.bookshop.dao;

import com.ccsu.bookshop.bean.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @create 2018-12-09-10:26
 */

public interface  LoginTicketDao {

   int addTicket(LoginTicket loginTicket);
   void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

   LoginTicket selectByTicket(String ticket);



}
