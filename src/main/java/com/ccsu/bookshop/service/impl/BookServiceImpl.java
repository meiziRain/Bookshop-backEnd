package com.ccsu.bookshop.service.impl;

import com.ccsu.bookshop.bean.Book;
import com.ccsu.bookshop.dao.BookDao;
import com.ccsu.bookshop.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public PageInfo<Book> getBooksByKind(String book_type_big, int pageNum, int pageSize) {

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);//分页初始化 初始页面为1，每页5条数据
        List<Book> list = bookDao.getBooksByKind(book_type_big);
        PageInfo<Book> info = new PageInfo<>(list);

        System.out.println(page.getPageNum());
        System.out.println(info.getPageNum());
        return info;
    }

    @Override
    public PageInfo<Book> selectByNewRecommend(int pageNum, int pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);//分页初始化 初始页面为1，每页5条数据
        List<Book> list = bookDao.selectByNewRecommend();
        PageInfo<Book> info = new PageInfo<>(list);
        System.out.println(page.getPageNum());
        System.out.println(info.getPageNum());
        return info;
    }

    @Override
    public List<String> selectByBigKind() {
        List<String> temp = bookDao.selectByBigKind();
        temp.remove("主编推荐");
        return temp;
    }

    @Override
    public List<Book> selectByBookCar() {
        return bookDao.selectByBookCar();
    }

    //wen
    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBookTopTen() {
        return bookDao.queryBookTopTen();
    }

    @Override
    public List<Book> queryBookByType(String bookType) {
        return bookDao.queryBookByType(bookType);
    }

    //    lyf
    @Override
    public PageInfo<Book> selectBypaixu(String paxiu, String kind, int pageNum, int pageSize) {
        paxiu += " desc";
        PageHelper.startPage(pageNum, pageSize, paxiu);  //发页初始化
        List<Book> list = bookDao.seletByindex(kind);  //查询记录
        PageInfo<Book> info = new PageInfo<>(list);  //放入分页列表
        return info;
    }

    @Override
    public PageInfo<Book> seletByindex(String kind, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);  //发页初始化
        List<Book> list = bookDao.seletByindex(kind);  //查询记录
        PageInfo<Book> info = new PageInfo<>(list);  //放入分页列表
        return info;
    }


    @Override
    public List<String> selectKind(String bookBigType) {
        return bookDao.selectKind(bookBigType);
    }

    @Override
    public PageInfo<Book> selectByLike(String key, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);  //发页初始化
        List<Book> list = bookDao.selectByLike(key); //查询记录
        PageInfo<Book> info = new PageInfo<>(list);  //放入分页列表
        return info;
    }

}
