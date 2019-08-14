package com.ccsu.bookshop.service.impl;

import com.ccsu.bookshop.bean.BookComment;
import com.ccsu.bookshop.dao.BookCommentDao;
import com.ccsu.bookshop.service.BookCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCommentServiceImpl implements BookCommentService {
    @Autowired
    private BookCommentDao bookCommentDao;



    @Override
    public PageInfo<BookComment> queryBookComments(Integer bookId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<BookComment> list =bookCommentDao.queryBookComments(bookId);
        PageInfo<BookComment>info=new PageInfo<>(list);
        return info;
    }

    @Override
    public int insertBookComment(BookComment bookComment) {
        return bookCommentDao.insertBookComment(bookComment);
    }

    @Override
    public int deleteBookCommentById(Integer id) {
        return bookCommentDao.deleteBookCommentById(id);
    }

    @Override
    public int updateCommentPraise(Integer likes, String likeMan, Integer id) {
        return bookCommentDao.updateCommentPraise(likes,likeMan,id);
    }
}
