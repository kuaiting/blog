package com.yuan.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.Blog;

import java.util.List;


public interface BlogService {
    /**
     * 添加博客
     */
    Integer addNewBlog(Blog blog);

    IPage<Blog> getBlogWithPage(Integer current,Integer size,Integer userId);

    Integer deletBlogById(String blogId);

    Integer updateBlogById(Blog blog,String blogId);

    //模糊查询当前用户的博客
    IPage<Blog> getBlogWithPage(Integer current,Integer size,String keywords,Integer userId);

    //模糊查询当前用户的博客
    IPage<Blog> searchBlogWithPage(Integer current, Integer size, String keywords);


    IPage<Blog> showBlogByStateAndPubType(Integer currentpage, Integer currentsize);

    Blog getDetailedBlogById(String blogId);
    IPage<Blog>  blogListByOderAgree();
    List<Blog> getBlogtByOderTime();


    List<Blog> showBlogByStateAndPubTypeAndTuijian();
}
