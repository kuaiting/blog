package com.yuan.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bean.Blog;
import com.yuan.bean.Books;
import com.yuan.mapper.BooksMapper;
import com.yuan.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BooksService {

    @Autowired
    private BooksMapper booksMapper;
    @Override
    public IPage<Books> getBookWithPage(Integer current, Integer size, Integer userId) {
        Page<Books> page = new Page<Books>(current, size);
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",1);
        queryWrapper.eq("user_id",userId);
        IPage<Books> iPage = booksMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public Integer deletBookById(Integer id) {
        Books books=new Books();
        books.setId(id);
        books.setState(0);
        int i = booksMapper.updateById(books);
        return i;
    }

    @Override
    public Integer addNewBook(Books books) {
        Integer res = booksMapper.insert(books);
        return res;
    }
}
