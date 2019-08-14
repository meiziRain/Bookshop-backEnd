package com.ccsu.bookshop.service;

import com.ccsu.bookshop.bean.BookComment;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookCommentService {
    PageInfo<BookComment> queryBookComments(Integer bookId, int pageNum, int pageSize);
    int insertBookComment(BookComment bookComment);
    int deleteBookCommentById(Integer id);

    int  updateCommentPraise(Integer likes, String likeMan, Integer id);
}
