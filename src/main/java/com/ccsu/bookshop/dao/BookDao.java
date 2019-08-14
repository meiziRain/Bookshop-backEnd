package com.ccsu.bookshop.dao;

import com.ccsu.bookshop.bean.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
    List<Book> getBooksByKind(String book_type_big);//根据种类查询书籍

    List<Book> selectByNewRecommend();//新书推荐

    List<String> selectByBigKind();//查询书的种类

    List<Book> selectByBookCar();//由购物车推荐

    //    wen
    Book queryBookById(Integer id);  //根据书的ID查询

    List<Book> queryBookTopTen();//查询图书榜前十

    List<Book> queryBookByType(String bookType);//同类型书

    //    lyf
    List<Book> seletByindex(String kind);



    //大分类找小分类
    List<String> selectKind(String bookBigType);

    List<Book> selectByLike(@Param("key") String key);


}


