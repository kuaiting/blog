package com.yuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.Blog;
import com.yuan.bean.Books;
import com.yuan.bean.Category;
import com.yuan.bean.Comments;
import com.yuan.services.BlogService;
import com.yuan.services.CategoryService;
import com.yuan.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.java2d.loops.Blit;
import sun.print.PeekGraphics;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/getConment")
    public  String getConmentBlogId(@RequestParam("blogId") String blogId,Model model, HttpSession session){
            session.setAttribute("blogId",blogId);
       List<Comments> list= commentService.getCommetList(blogId);
        model.addAttribute("commentlist",list);
        return "comment";
    }
    @RequestMapping("/addConment")
    public  String addConment(@RequestParam("msg") String msg, Model model,HttpSession session){
        String  id = session.getAttribute("blogId").toString();
        Date date = new Date(System.currentTimeMillis());
        Comments comment=new Comments();
        comment.setState(1);
        comment.setBlogId(id);
        comment.setMsg(msg);
        comment.setCreateTime(date);
        comment.setUpdateTime(date);
        Blog detailed = blogService.getDetailedBlogById(id);
        Integer n = detailed.getComment();
        Blog blog=new Blog();
        blog.setComment(n+1);
        blogService.updateBlogById(blog,id);
        commentService.addComment(comment);
        List<Comments> list= commentService.getCommetList(id);
        model.addAttribute("commentlist",list);
       return "comment";
    }
}
