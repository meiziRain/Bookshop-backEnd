package com.ccsu.bookshop.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccsu.bookshop.bean.Book;

import com.ccsu.bookshop.bean.CookieBook;
import org.junit.Test;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 我的购物车
 * @author Administrator
 *
 */
public class CookieUtil {


    /**
     * 获取名为"cart"的cookie
     *
     * @param request
     * @return cookie
     */
    public  static  Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cart_cookie = null;
        for (Cookie cookie : cookies) {
            if ("cart".equals(cookie.getName())) { //获取购物车cookie
                cart_cookie = cookie;
            }
        }

        return cart_cookie;
    }

    /**
     * 获取cookie中的购物车列表
     *
     * @param response
     * @param request
     * @return 购物车列表
     * @throws UnsupportedEncodingException 抛出异常
     */
    public static  List<CookieBook> getCartInCookie(HttpServletResponse response, HttpServletRequest request) throws
            Exception {
        // 定义空的购物车列表
        List<CookieBook> items = new ArrayList<CookieBook>();
        String value_1st = "";
        // 购物cookie
        Cookie cart_cookie = getCookie(request);
        // 判断cookie是否为空
        if (cart_cookie != null) {
            // 获取cookie中String类型的value
            value_1st = URLDecoder.decode(cart_cookie.getValue(), "utf-8");//从cookie获取购物车
            // 判断value是否为空或者""字符串
            if (value_1st != null && !"".equals(value_1st)) {
                // 解析字符串中的数据为对象并封装至list中返回给上一级
                String[] arr_1st = value_1st.split("==");

                for (String value_2st : arr_1st) {
                    String[] arr_2st = value_2st.split("=");
                    CookieBook item = new CookieBook();
                    item.setId(Integer.parseInt(arr_2st[0])); //商品id
                    item.setNum(Integer.parseInt(arr_2st[1]));
                    item.setDateTime(DateUtil.formatString( arr_2st[2],"yyyy-MM-dd HH:mm:ss"));
                    items.add(item);
                }
            }
        }
        //返回list BOOK
        return items;

    }


    /**
     * 制作cookie所需value
     *
     *
     * @return 解析为字符串的购物车列表，属性间使用"="相隔，对象间使用"=="相隔
     */


  public static  String makeCookieValue(List<CookieBook> books){

      StringBuffer buffer_2st = new StringBuffer();
      for (CookieBook item : books) {
        buffer_2st.append(item.getId() + "=" + item.getNum() +"="+item.getDateTime()+"==");
    }
      return buffer_2st.toString().substring(0, buffer_2st.toString().length() - 2);
    }


    @Test
    public  void t (){
        List<CookieBook> books =new ArrayList<>();
        CookieBook book =new CookieBook();
        CookieBook book1 =new CookieBook();
        book.setId(1);
        book.setNum(11);
        book1.setId(2);
        book1.setNum(222);
        books.add(book);
        books.add(book1);
        CookieUtil t =new CookieUtil();

        System.out.println(t.makeCookieValue(books));
    }























}




