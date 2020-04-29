package com.yuan.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.Blog;
import com.yuan.bean.Category;
import com.yuan.bean.User;
import com.yuan.services.BlogService;
import com.yuan.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowBlogController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;



    @RequestMapping("/tuijian")
    public String tuijian(@RequestParam("blogId") String blogId,Model model,HttpSession session){
        Blog blog1 = new Blog();
        blog1.setTuijian(1);
        blogService.updateBlogById(blog1, blogId);

        Integer currentpage = 1;
        Integer currentsize = 5;

        IPage<Blog> blogIPage = blogService.showBlogByStateAndPubType(currentpage, currentsize);

        List<Category> categorys = categoryService.getAllCategoryByState();

        long current1 = blogIPage.getCurrent();
        long pages = blogIPage.getPages();
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }
        List<Blog> tuijianlist=blogService.showBlogByStateAndPubTypeAndTuijian();
        model.addAttribute("tuijianlist", tuijianlist);

        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("total", total);
        model.addAttribute("records", records);
        model.addAttribute("categories", categorys);
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "login";
        }
        model.addAttribute("name", user.getLoginName());

        return "index";
    }

    @RequestMapping("/notuijian")
    public String notuijian(@RequestParam("blogId") String blogId,Model model,HttpSession session){
        Blog blog1 = new Blog();
        blog1.setTuijian(0);
        blogService.updateBlogById(blog1, blogId);
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "login";
        }
        Integer currentpage = 1;
        Integer currentsize = 5;

        IPage<Blog> blogIPage = blogService.showBlogByStateAndPubType(currentpage, currentsize);

        List<Category> categorys = categoryService.getAllCategoryByState();

        long current1 = blogIPage.getCurrent();
        long pages = blogIPage.getPages();
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }
        List<Blog> tuijianlist=blogService.showBlogByStateAndPubTypeAndTuijian();
        model.addAttribute("tuijianlist", tuijianlist);

        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("total", total);
        model.addAttribute("records", records);
        model.addAttribute("categories", categorys);

        model.addAttribute("name", user.getLoginName());

        return "index";
    }

    @RequestMapping("/zuinewblog")
    @ResponseBody
    public Blog  newblog() {

        List<Blog> blogList = blogService.getBlogtByOderTime();
        Blog blog = blogList.get(0);

        return blog;
    }

    @RequestMapping("/getblogOrderAgree")
    @ResponseBody
    public List<Blog> blogListByOderAgree() {
        IPage<Blog> blogs = blogService.blogListByOderAgree();
        List<Blog> records = blogs.getRecords();

        return records;
    }

    @RequestMapping("/addagree")
    public String addAgree(@RequestParam("blogId") String blogId, Model model,HttpSession session) {



        Blog blog = blogService.getDetailedBlogById(blogId);
        Integer agree = blog.getAgree();
        Blog blog1 = new Blog();
        blog1.setAgree(agree + 1);
        blogService.updateBlogById(blog1, blogId);

        Integer currentpage = 1;
        Integer currentsize = 5;

        IPage<Blog> blogIPage = blogService.showBlogByStateAndPubType(currentpage, currentsize);

        List<Category> categorys = categoryService.getAllCategoryByState();

        long current1 = blogIPage.getCurrent();
        long pages = blogIPage.getPages();
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }
        List<Blog> tuijianlist=blogService.showBlogByStateAndPubTypeAndTuijian();
        model.addAttribute("tuijianlist", tuijianlist);

        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("total", total);
        model.addAttribute("records", records);
        model.addAttribute("categories", categorys);


        return "index";
    }


    @RequestMapping("/getDetailed")
    public String getDetailed(@RequestParam("blogId") String blogId, Model model) {
        Blog blog = blogService.getDetailedBlogById(blogId);
        model.addAttribute("blogDetailed", blog);
        return "blogshow";
    }

    @RequestMapping("/searchBlog")
    public String searchBlog( @RequestParam("keywords") String keywords, Model model, HttpSession session) {

        Integer currentpage = 1;
        Integer currentsize = 5;


        IPage<Blog> blogIPage = blogService.searchBlogWithPage(currentpage, currentsize, keywords);


        List<Category> categorys = categoryService.getAllCategoryByState();

        long current1 = blogIPage.getCurrent();
        long pages = blogIPage.getPages();
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }
        List<Blog> tuijianlist=blogService.showBlogByStateAndPubTypeAndTuijian();
        model.addAttribute("tuijianlist", tuijianlist);
        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("total", total);
        model.addAttribute("records", records);
        model.addAttribute("categories", categorys);

        return "index";
    }

    @RequestMapping("/index")
    public String showBloginfo(Model model, HttpSession session) {
        Integer currentpage = 1;
        Integer currentsize = 5;
        User user = (User) session.getAttribute("user");

        IPage<Blog> blogIPage = blogService.showBlogByStateAndPubType(currentpage, currentsize);

        List<Category> categorys = categoryService.getAllCategoryByState();

        long current1 = blogIPage.getCurrent();
        long pages = blogIPage.getPages();
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }
        if(user==null){
            return "login";
        }
        model.addAttribute("name", user.getLoginName());
        model.addAttribute("user", user);
        List<Blog> tuijianlist=blogService.showBlogByStateAndPubTypeAndTuijian();
        model.addAttribute("tuijianlist", tuijianlist);
        model.addAttribute("user", user);
        model.addAttribute("name", user.getLoginName());
        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("total", total);
        model.addAttribute("records", records);
        model.addAttribute("categories", categorys);


        return "index";
    }

    @RequestMapping("/")
    public String showBlog(Model model, HttpSession session) {
        Integer currentpage = 1;
        Integer currentsize = 5;

        List<Blog> tuijianlist=blogService.showBlogByStateAndPubTypeAndTuijian();

        IPage<Blog> blogIPage = blogService.showBlogByStateAndPubType(currentpage, currentsize);

        List<Category> categorys = categoryService.getAllCategoryByState();

        long current1 = blogIPage.getCurrent();
        long pages = blogIPage.getPages();
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }

        model.addAttribute("tuijianlist", tuijianlist);
        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("total", total);
        model.addAttribute("records", records);
        model.addAttribute("categories", categorys);

        return "index";
    }

    @RequestMapping("/showblog")

    public String showBlogWithPage(@RequestParam("current") Integer current, @RequestParam("size") Integer size, Model model, HttpSession session) {

        Integer currentpage = 1;
        Integer currentsize = 5;

        if (size != null) {
            currentsize = size;
        }
        if (current != null) {
            currentpage = current;
        }

        IPage<Blog> blogIPage = blogService.showBlogByStateAndPubType(currentpage, currentsize);

        List<Category> categorys = categoryService.getAllCategoryByState();

        long current1 = blogIPage.getCurrent();
        long pages = blogIPage.getPages();
        List<Blog> records = blogIPage.getRecords();
        long total = blogIPage.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }
        List<Blog> tuijianlist=blogService.showBlogByStateAndPubTypeAndTuijian();
        model.addAttribute("tuijianlist", tuijianlist);
        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("total", total);
        model.addAttribute("records", records);
        model.addAttribute("categories", categorys);

        return "index";
    }


}
