package com.ccsu.bookshop.controller;

import com.ccsu.bookshop.bean.Book;
import com.ccsu.bookshop.bean.CookieBook;
import com.ccsu.bookshop.service.BookService;
import com.ccsu.bookshop.utils.CookieUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

/**
 * @create 2018-12-10-21:19
 */

@Controller
@RequestMapping("/cookie")
public class CookieCarController {
  @Autowired
  private BookService bookService;
    //添加产品到购物车
    @RequestMapping("/addBookCar")
        public void addGoodsToCart(@RequestParam("Integer") Integer booksId, @RequestParam("num") Object num ,HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        // 从cookie中获取购物车列表
        List<CookieBook> books = CookieUtil.getCartInCookie(response, request);

        Book book=null;
        Cookie cookie_2st;
        if (books.size()<=0){

            CookieBook cookieBook =new CookieBook();
            cookieBook.setId(booksId);
            cookieBook.setNum(setNum(num));
            cookieBook.setDateTime(new Date());
            books.add(cookieBook);

            if (CookieUtil.getCookie(request) == null){
                cookie_2st = new Cookie("cart", URLEncoder.encode(CookieUtil.makeCookieValue(books), "utf-8"));
                cookie_2st.setPath("/");//设置在该项目下都可以访问该cookie
                cookie_2st.setMaxAge(60 * 30);//设置cookie有效时间为30分钟
                response.addCookie(cookie_2st);//添加cookie
            }else {

                cookie_2st =CookieUtil. getCookie(request);
                cookie_2st.setPath("/");//设置在该项目下都可以访问该cookie
                cookie_2st.setMaxAge(60 * 30);//设置cookie有效时间为30分钟
                cookie_2st.setValue(URLEncoder.encode(CookieUtil.makeCookieValue(books)));
                response.addCookie(cookie_2st);//添加cookie
            }
        }

        // 当获取的购物车列表不为空时
        else {
            int bj = 0;
            for (CookieBook book1 : books) {
                // 如果购物车中存在该商品则数量+1
                if (book1.getId() == booksId) {
                    book1.setNum(book1.getNum() +setNum(num));

                    bj = 1;
                    break;
                }
            }
            if (bj == 0) {
                //TODO 根据书本ID获取书本信息
                CookieBook cookieBook =new CookieBook();
                cookieBook.setId(booksId);
                cookieBook.setNum(setNum(num));
                cookieBook.setDateTime(new Date());
                books.add(cookieBook);
            }

            // 获取名为"cart"的cookie
            cookie_2st =CookieUtil. getCookie(request);
            cookie_2st.setPath("/");//设置在该项目下都可以访问该cookie
            cookie_2st.setMaxAge(60 * 30);//设置cookie有效时间为30分钟
            cookie_2st.setValue(URLEncoder.encode(CookieUtil.makeCookieValue(books))); // 设置value
            response.addCookie(cookie_2st);//添加cookie
        }

    }



    private  int setNum(Object num){
        if (num==null){
            return  1;
        }
        return (int)num;
    }

    @RequestMapping("/deleteBybookId")
    public String deleteByGoodsId( Integer []booksId, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        // 获取cookie中购物车列表
        List<CookieBook> books =CookieUtil. getCartInCookie(response, request);
        CookieBook book = null;
        // 判断购物车列表是否为空
        if (books.size() > 0) {
            for (int i = 0; i <booksId.length ; i++) {
            // 循环购物车列表寻找相同ID的商品
            for (CookieBook c : books) {
                if (c.getId().equals(booksId[i])) {
                    book = c;
                    break;
                }
            }
            // 判断是否找到相同ID的商品
            if (book != null) {

                    //删除该书信息
                    books.remove(book);

            }
                // 获取名为"cart"的cookie
                Cookie cookie = CookieUtil.getCookie(request);
                // 为cookie设置value
                cookie.setValue(URLEncoder.encode(CookieUtil.makeCookieValue(books), "utf-8"));
                // 设置寿命
                cookie.setMaxAge(60 * 10);
                // 设置路径
                cookie.setPath("/");
                // 更新cookie
                response.addCookie(cookie);
            }

        }
        return "success";
    }





    /**
     * 清空购物车
     *
     * @param response
     * @param request
     * @return 成功与否
     */

    @RequestMapping("/deleteCookie")
    public String deleteCookie(HttpServletResponse response, HttpServletRequest request) {
        // 获取名为"cart"的cookie
        Cookie cookie = CookieUtil.getCookie(request);
        // 设置寿命为0秒
        cookie.setMaxAge(0);
        // 设置路径
        cookie.setPath("/");
        // 设置cookie的value为null
        cookie.setValue(null);
        // 更新cookie
        response.addCookie(cookie);

        return "success";
    }


    /**
     * 获取购物车列表
     *
     * @param request
     * @param response
     * @return 购物车列表
     * @throws UnsupportedEncodingException 抛出异常
     */

    @RequestMapping("/selectByBookCar")
    @ResponseBody
    public Map<Book,Integer> getBooks(HttpServletRequest request, HttpServletResponse response) throws
            Exception {
        List<CookieBook> result= CookieUtil.getCartInCookie(response, request);
        Map<Book,Integer> map = new HashMap<>();
        for (int i = 0; i <result.size(); i++) {
            Book book= new Book();
            book=bookService.queryBookById(result.get(i).getId());
            map.put(book,result.get(i).getNum());
        }
        return map;

    }


    //点击  +
    @RequestMapping("/updateByNum")

    public void updateByNumadd(@RequestParam("Integer") Integer booksId, @RequestParam("num") Object num ,HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取cookie中购物车列表
        List<CookieBook> books =CookieUtil. getCartInCookie(response, request);

        CookieBook book = null;
        // 判断购物车列表是否为空

        if (books.size() > 0) {
                // 循环购物车列表寻找相同ID的商品
                for (CookieBook c : books) {
                    if (c.getId().equals(booksId)) {
                        book = c;
                        break;
                    }
                }
                // 判断是否找到相同ID的商品
                if (book != null) {
                    book.setNum(setNum(num));

                    // 获取名为"cart"的cookie
                    Cookie cookie = CookieUtil.getCookie(request);
                    // 为cookie设置value
                    cookie.setValue(URLEncoder.encode(CookieUtil.makeCookieValue(books), "utf-8"));
                    // 设置寿命
                    cookie.setMaxAge(60 * 10);
                    // 设置路径
                    cookie.setPath("/");
                    // 更新cookie
                    response.addCookie(cookie);
                }
        }



    }


}
