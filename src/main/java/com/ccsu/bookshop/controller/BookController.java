package com.ccsu.bookshop.controller;

import com.ccsu.bookshop.bean.Book;
import com.ccsu.bookshop.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller


public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooksByKind")
    @ResponseBody
    public PageInfo<Book> getBooksByKind(String kind, int pageNum,
                                         int pageSize) {

        return bookService.getBooksByKind(kind, pageNum, pageSize);

    }


    @RequestMapping("/selectByLike")
    @ResponseBody
    public PageInfo<Book> selectByLike(String key, int pageNum,
                                         int pageSize) {
        return bookService.selectByLike(key, pageNum, pageSize);

    }

    @RequestMapping("/selectByNewRecommend")
    @ResponseBody
    public PageInfo<Book> selectByNewRecommend(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return bookService.selectByNewRecommend(pageNum, pageSize);
    }

    @RequestMapping("/selectByBigKind")
    @ResponseBody
    public List<String> selectByBigKind() {
        return bookService.selectByBigKind();
    }

    @RequestMapping("/selectByBookCar")
    @ResponseBody
    public List<Book> selectByBookCar() {
        return bookService.selectByBookCar();
    }

    //wen
    @RequestMapping("/queryBookById")
    @ResponseBody
    public Book queryBookById(@RequestParam Integer id) {
        return bookService.queryBookById(id);
    }

    @RequestMapping("/queryBookTopTen")
    @ResponseBody
    public List<Book> queryBookTopTen() {
        return bookService.queryBookTopTen();
    }

    @RequestMapping("/queryBookByType")
    @ResponseBody
    public List<Book> queryBookByType(@RequestParam String bookType) {
        return bookService.queryBookByType(bookType);
    }

    //    lyf
    @RequestMapping("/selectBypaixu")
    @ResponseBody
    public PageInfo<Book> selectBypaixu(String paXiu, String kind, int pageNum, int pageSize) {
        PageInfo<Book> list = bookService.selectBypaixu(paXiu, kind, pageNum, pageSize);
        return list;
    }

    @RequestMapping("/seletByindex")
    @ResponseBody
    public PageInfo<Book> seletByindex(String kind, int pageNum, int pageSize) {
        PageInfo<Book> list = bookService.seletByindex(kind, pageNum, pageSize);
        return list;
    }


    @RequestMapping("/selectKind")
    @ResponseBody
    public List<String> selectKind(String bigKind){
        List<String> T=  bookService.selectKind(bigKind);

        return  T;
    }

}
