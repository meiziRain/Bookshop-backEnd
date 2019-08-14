package com.ccsu.bookshop.controller;

import com.ccsu.bookshop.bean.BookComment;
import com.ccsu.bookshop.service.BookCommentService;
import com.ccsu.bookshop.utils.SensitivewordFilter;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/BookComment")
public class BookCommentController {

    @Autowired
    private BookCommentService bookCommentService;

    @RequestMapping("/queryBookComments")
    @ResponseBody
    public PageInfo<BookComment> queryBookComments(Integer bookId, int pageNum, int pageSize) {
        return bookCommentService.queryBookComments(bookId, pageNum, pageSize);
    }

    @RequestMapping("/insertBookComment")
    @ResponseBody

    public int insertBookComment(BookComment bookComment) {

        SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();
        bookComment.setContent(sensitivewordFilter.doWordsFilter(bookComment.getContent()));
        return bookCommentService.insertBookComment(bookComment);
    }

    @RequestMapping("/deleteBookCommentById")
    @ResponseBody
    public int deleteBookCommentById(@RequestParam Integer id) {

        return bookCommentService.deleteBookCommentById(id);
    }

    @RequestMapping("/updateCommentPraise")
    @ResponseBody
    public int updateCommentPraise(@RequestParam Integer likes,@RequestParam String likeMan,@RequestParam Integer id) {

        return bookCommentService.updateCommentPraise(likes,likeMan,id);
    }}
