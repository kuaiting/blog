package com.yuan.services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bean.Comments;
import com.yuan.mapper.CommentMapper;
import com.yuan.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Integer addComment(Comments comment) {
        int insert = commentMapper.insert(comment);
        return insert;
    }

    @Override
    public List<Comments> getCommetList(String blogId) {
      QueryWrapper<Comments> querywrapper=new QueryWrapper<>();
      querywrapper.eq("blog_id",blogId);
        List<Comments> list = commentMapper.selectList(querywrapper);
        return list;
    }
}
