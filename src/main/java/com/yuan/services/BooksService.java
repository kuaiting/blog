package com.yuan.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.Books;

public interface BooksService {

    /**
     * 分页查询我的博客留言
     * @param current
     * @param size
     * @param userId
     * @return
     */
    IPage<Books> getBookWithPage(Integer current, Integer size, Integer userId);

    Integer deletBookById(Integer id);

    Integer addNewBook(Books books);
}
