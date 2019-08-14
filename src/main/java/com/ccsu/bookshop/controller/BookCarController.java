package com.ccsu.bookshop.controller;

import com.ccsu.bookshop.bean.BookCar;
import com.ccsu.bookshop.service.BookCarService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookCarController {

    @Autowired
    private BookCarService bookCarService;

    @RequestMapping("/selectBookCarByUserId")
    @ResponseBody
    public PageInfo<BookCar>selectBookCarByUserId(Integer userId,int pageNum,int pageSize){

        return bookCarService.selectBookCarByUserId(userId,pageNum,pageSize);
    }

    @RequestMapping("/updateBookCarByNum")
    @ResponseBody
    public void updateBookCarByNum(BookCar bookCar){
        bookCarService.updateBookCarByNum(bookCar);
    }

    @RequestMapping("/deleteBookCarById")
    @ResponseBody
    public void deleteBookCarById(@RequestParam(value = "deleteId",defaultValue = "0") int[] deleteId){
        bookCarService.deleteBookCarById(deleteId);
    }

    @RequestMapping("/insertBookCar")
    @ResponseBody
    public int insertBookCar(BookCar bookCar){
        BookCar bookCar1=bookCarService.selectBookCar(bookCar.getUserId(),bookCar.getBookId());
        if(bookCar1!=null){
            bookCarService.updateBookCarByNum(bookCar);
            return  1;
        }
        else {
            bookCarService.insertBookCar(bookCar);
            return  1;
        }
    }

    @RequestMapping("/countBookCarByUserId")
    @ResponseBody
    public int countBookCarByUserId(Integer userId){
        return bookCarService.countBookCarByUserId(userId);
    }

    @RequestMapping("/updateBookCarByStauts")
    @ResponseBody
    public int updateBookCarByStauts(@RequestParam(value = "updateId",defaultValue = "0") int[] updateId){
        return bookCarService.updateBookCarByStauts(updateId);
    }

    @RequestMapping("/selectBookCarByPayed")
    @ResponseBody
    public List<BookCar>selectBookCarByPayed(Integer userId){
        return bookCarService.selectBookCarByPayed(userId);
    }


    @RequestMapping("/updateBookCarFromDetail")
    @ResponseBody
    public  int updateBookCarFromDetail(BookCar bookCar){
        return bookCarService.updateBookCarFromDetail(bookCar);
    }
}
