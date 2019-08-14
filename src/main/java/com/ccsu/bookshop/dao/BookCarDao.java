package com.ccsu.bookshop.dao;

import com.ccsu.bookshop.bean.BookCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookCarDao {

    List<BookCar>selectBookCarByUserId(Integer userId);//根据用户id查询所属购物车

    List<BookCar>selectBookCarByPayed(Integer userId);//根据用户id查询已购买商品

    public void updateBookCarByNum(BookCar bookCar);//修改购物车商品数量

    public void deleteBookCarById(int[] id);//删除单个或批量删除

    int insertBookCar(BookCar bookCar);//新增购物车信息

    Object countBookCarByUserId(Integer userId);//统计购物车商品数量

    BookCar selectBookCar(@Param("userId") Integer userId,@Param("bookId") Integer bookId);//查询某个商品是否存在购物车中

    int updateBookCarByStauts(int[] updateId);//完成订单，修改购物车商品的状态

//wen
    int  updateBookCarFromDetail(@Param("buyNum") Integer buyNum,@Param("bookId") Integer bookId,@Param("userId") Integer userId);

    int insertBookCarFromDetail(BookCar bookCar);
}
