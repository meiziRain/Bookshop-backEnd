package com.ccsu.bookshop.service;

import com.ccsu.bookshop.bean.BookCar;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookCarService {
    PageInfo<BookCar>selectBookCarByUserId(Integer userId,int pageNum,int pageSize);//根据用户id查询购物车

    public void updateBookCarByNum(BookCar bookCar);

    public  void deleteBookCarById(int[] id);

    int insertBookCar(BookCar bookCar);

    int countBookCarByUserId(Integer userId);

    BookCar selectBookCar(Integer userId,Integer bookId);

    int updateBookCarByStauts(int[] updateId);

    List<BookCar>selectBookCarByPayed(Integer userId);

    int  updateBookCarFromDetail(BookCar bookCar);
}
