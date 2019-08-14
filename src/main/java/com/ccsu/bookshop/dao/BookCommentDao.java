package com.ccsu.bookshop.dao;

import com.ccsu.bookshop.bean.BookComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookCommentDao {

    List<BookComment> queryBookComments(Integer bookId);

    int insertBookComment(BookComment bookComment);

    int deleteBookCommentById(Integer id);

    int  updateCommentPraise(@Param("likes") Integer likes, @Param("likeMan") String likeMan, @Param("id") Integer id);
}
