package com.yuan.services;

import com.yuan.bean.Comments;

import java.util.List;

public interface CommentService {
    Integer addComment(Comments comment);

    List<Comments> getCommetList(String blogId);
}
