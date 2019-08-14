package com.ccsu.bookshop.bean;

import java.util.Date;

/**
 * @create 2018-12-10-21:11
 */
public class CookieBook {

    //Bookid
    private  Integer id;

    //数量
    private  int num;

    //时间
    private Date dateTime;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}