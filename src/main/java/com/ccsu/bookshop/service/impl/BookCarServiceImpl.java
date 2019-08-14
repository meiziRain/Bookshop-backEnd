package com.ccsu.bookshop.service.impl;

import com.ccsu.bookshop.bean.BookCar;
import com.ccsu.bookshop.dao.BookCarDao;
import com.ccsu.bookshop.service.BookCarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookCarServiceImpl implements BookCarService {

    @Autowired
    private BookCarDao bookCarDao;

    @Override
    public PageInfo<BookCar> selectBookCarByUserId(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页初始化 初始页面为1，每页5条数据
        List<BookCar>list=bookCarDao.selectBookCarByUserId(userId);
        PageInfo<BookCar>info=new PageInfo<>(list);
        return info;
    }

    @Override
    public void updateBookCarByNum(BookCar bookCar) {
        bookCarDao.updateBookCarByNum(bookCar);
    }


    @Override
    public void deleteBookCarById(int[] id) {
        bookCarDao.deleteBookCarById(id);
    }

    @Override
    public int insertBookCar(BookCar bookCar) {
        String date =null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        date=dateFormat.format(new Date());
        System.out.println(date);
        bookCar.setCreateTime(date);
        System.out.println(bookCar);
        return bookCarDao.insertBookCar(bookCar);
    }

    @Override
    public int countBookCarByUserId(Integer userId){
        if (bookCarDao.countBookCarByUserId(userId)==null)
        {
            return  0 ;
        }
        else{
            return  (int)bookCarDao.countBookCarByUserId(userId);
        }
    }

    @Override
    public BookCar selectBookCar(Integer userId, Integer bookId) {
        return bookCarDao.selectBookCar(userId,bookId);
    }

    @Override
    public int updateBookCarByStauts(int[] updateId) {
        return bookCarDao.updateBookCarByStauts(updateId);
    }

    @Override
    public List<BookCar> selectBookCarByPayed(Integer userId) {
        return bookCarDao.selectBookCarByPayed(userId);
    }


//wen
    @Override
    public int updateBookCarFromDetail(BookCar bookCar) {
        if (bookCarDao.selectBookCar(bookCar.getUserId(),bookCar.getBookId())!=null){
            return bookCarDao.updateBookCarFromDetail(bookCar.getNumber(),bookCar.getBookId(),bookCar.getUserId());
        }
        else {
            return  bookCarDao.insertBookCarFromDetail(bookCar);
        }
    }

}

