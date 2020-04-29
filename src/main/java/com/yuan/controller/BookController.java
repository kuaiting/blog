package com.yuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.Blog;
import com.yuan.bean.Books;
import com.yuan.bean.Category;
import com.yuan.bean.User;
import com.yuan.services.BlogService;
import com.yuan.services.BooksService;
import com.yuan.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BooksService booksService;

    @Autowired
    private  BlogService blogService;
    @Autowired
    private  CategoryService categoryService;


    @RequestMapping("/addnewbook")
    public String  addNewBook(@RequestParam("book") String book, Model model,HttpSession session){

      String id = session.getAttribute("bookblogId").toString();
        User user = (User) session.getAttribute("user");

        Date date = new Date(System.currentTimeMillis());
        Books books=new Books();
        books.setState(1);
        books.setUserId(user.getId());
        books.setBlogId(id);
        books.setBook(book);
        books.setCreateTime(date);
        books.setUpdateTime(date);
       Integer result= booksService.addNewBook(books);
       if(result!=null){
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
       }else {
           return "book";
       }


    }

    @RequestMapping("/tobookpage")
    public String toBookPage(){
        return "book";
    }


    @RequestMapping("/getBookBlogId")

    public String getBookBlogId(@RequestParam("blogId") String blogId, Model model, HttpSession session) {
        session.setAttribute("bookblogId",blogId);
        User user = (User) session.getAttribute("user");
        if(user==null){
            model.addAttribute("msg","您还没有登录，请先登录，后使用");
            return "login";
        }else {
           model.addAttribute("bookblogId",blogId);
            return "book";
        }


    }

    @RequestMapping("/tobooklist")
    public String bookList(@RequestParam("current") Integer current, @RequestParam("size") Integer size, HttpSession session, Model model) {
        Integer currentpage = 1;
        Integer pagesize = 5;
        if (current != null) {
            currentpage = current;
        }
        if (size != null) {
            pagesize = size;
        }
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        IPage<Books> books = booksService.getBookWithPage(currentpage, pagesize, id);
        long current1 = books.getCurrent();
        long pages = books.getPages();
        List<Books> records = books.getRecords();
        long total = books.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }

        model.addAttribute("current", current1);
        model.addAttribute("total", total);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("records", records);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("pages", pages);
        model.addAttribute("user", user);
        return "booklist";
    }

    @RequestMapping("/deletbook")
    public String deletBook(@RequestParam("id") Integer id, HttpSession session, Model model) {
        Integer res = booksService.deletBookById(id);
        if (res != null) {
            Integer currentpage = 1;
            Integer pagesize = 5;

            User user = (User) session.getAttribute("user");
            Integer id1 = user.getId();
            IPage<Books> books = booksService.getBookWithPage(currentpage, pagesize, id1);
            long current1 = books.getCurrent();
            long pages = books.getPages();
            List<Books> records = books.getRecords();
            long total = books.getTotal();
            List<Integer> pagenum = new ArrayList<>();
            for (int i = 1; i < pages; i++) {
                pagenum.add(i);
            }

            model.addAttribute("current", current1);
            model.addAttribute("total", total);
            model.addAttribute("records", records);
            model.addAttribute("pagenum", pagenum);
            model.addAttribute("pages", pages);
            model.addAttribute("user", user);
            return "booklist";
        } else {
            return "404";
        }

    }

    @PostMapping("/deletportion")

    public String deletPortionBook(@RequestParam("checkbox") Integer[] checkbox, HttpSession session, Model model) {
        for (int i = 0; i < checkbox.length; i++) {
            Integer id = checkbox[i];
            booksService.deletBookById(id);
        }
        Integer currentpage = 1;
        Integer pagesize = 5;

        User user = (User) session.getAttribute("user");
        Integer id1 = user.getId();
        IPage<Books> books = booksService.getBookWithPage(currentpage, pagesize, id1);
        long current1 = books.getCurrent();
        long pages = books.getPages();
        List<Books> records = books.getRecords();
        long total = books.getTotal();
        List<Integer> pagenum = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            pagenum.add(i);
        }

        model.addAttribute("current", current1);
        model.addAttribute("total", total);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("records", records);
        model.addAttribute("pagenum", pagenum);
        model.addAttribute("pages", pages);
        model.addAttribute("user", user);
        return "booklist";
    }
}
