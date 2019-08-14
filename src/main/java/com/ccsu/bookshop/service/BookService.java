package com.ccsu.bookshop.service;

import com.ccsu.bookshop.bean.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookService {

    PageInfo<Book> getBooksByKind(String book_type_big, int pageNum, int pageSize);//按种类查询书

    PageInfo<Book> selectByNewRecommend(int pageNum, int pageSize);//新书推荐

    List<String> selectByBigKind();//查询大分类

    List<Book> selectByBookCar();//根据购物车推荐


    //    wen
    Book queryBookById(Integer id);  //根据书的ID查找

    List<Book> queryBookTopTen();  //图书前十

    List<Book> queryBookByType(String bookType);//同类型书

    //    lyf
    PageInfo<Book> selectBypaixu(String paxiu, String kind, int pageNum, int pageSize);
    PageInfo<Book> seletByindex(String kind,int pageNum,int pageSize);

    List<String> selectKind(String bookBigType);
    PageInfo<Book> selectByLike(String key,int pageNum,int pageSize);
}
