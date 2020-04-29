package com.yuan.services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bean.Blog;
import com.yuan.bean.User;
import com.yuan.mapper.BlogMapper;
import com.yuan.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Integer addNewBlog(Blog blog) {
        int insert = blogMapper.insert(blog);
        return insert;
    }

    @Override
    public IPage<Blog> blogListByOderAgree() {

        Page<Blog> page = new Page<Blog>(1, 5);
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        wrapper.orderByDesc("agree");
        wrapper.eq("pubtype_id", 1);


        IPage<Blog> iPage = blogMapper.selectPage(page, wrapper);
        return iPage;
    }

    @Override
    public List<Blog> getBlogtByOderTime() {

        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        wrapper.orderByDesc("create_time");
        wrapper.eq("pubtype_id", 1);
        List<Blog> blogs = blogMapper.selectList(wrapper);
        return blogs;
    }

    @Override
    public List<Blog> showBlogByStateAndPubTypeAndTuijian() {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        wrapper.eq("tuijian", 1);
        wrapper.orderByDesc("create_time");
        wrapper.eq("pubtype_id", 1);
        List<Blog> blogs = blogMapper.selectList(wrapper);
        return blogs;
    }

    @Override
    public IPage<Blog> getBlogWithPage(Integer current, Integer size, Integer userId) {
        Page<Blog> page = new Page<Blog>(current, size);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 1);
        queryWrapper.eq("user_id", userId);
        IPage<Blog> iPage = blogMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    //删除博客（更改状态即可）
    @Override
    public Integer deletBlogById(String blogId) {
        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setState(0);
        int res = blogMapper.updateById(blog);
        return res;
    }

    @Override
    public Integer updateBlogById(Blog blog, String blogId) {

        UpdateWrapper<Blog> uptateWrapper = new UpdateWrapper<>();
        uptateWrapper.eq("id", blogId);
        int update = blogMapper.update(blog, uptateWrapper);
        return update;
    }

    @Override
    public IPage<Blog> getBlogWithPage(Integer current, Integer size, String keywords, Integer userId) {
        Page<Blog> page = new Page<Blog>(current, size);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 1);
        queryWrapper.eq("user_id", userId);

        queryWrapper.like("keywords", keywords);

        IPage<Blog> iPage = blogMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public IPage<Blog> searchBlogWithPage(Integer current, Integer size, String keywords) {

        Page<Blog> page = new Page<Blog>(current, size);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 1);
        queryWrapper.eq("pubtype_id", 1);
        //queryWrapper.like("tags",keywords);
        queryWrapper.like("keywords", keywords);

        IPage<Blog> iPage = blogMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public IPage<Blog> showBlogByStateAndPubType(Integer currentpage, Integer currentsize) {

        Page<Blog> page = new Page<Blog>(currentpage, currentsize);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 1);
        queryWrapper.eq("pubtype_id", 1);
        IPage<Blog> iPage = blogMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public Blog getDetailedBlogById(String blogId) {
        Blog blog = blogMapper.selectById(blogId);
        return blog;
    }
}
