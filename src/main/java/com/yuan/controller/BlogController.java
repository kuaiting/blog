package com.yuan.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.*;
import com.yuan.constant.GlobalConst;
import com.yuan.services.*;
import com.yuan.utlis.ImgResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private SourceService sourceService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PubtypeService pubtypeService;

    @Autowired
    private BlogService blogService;


    @RequestMapping("/searchblog")
    public String searchblog(@RequestParam("keywords") String keywords,@RequestParam("size")  Integer size, HttpSession session, Model model) {
        //分页查询
        Integer current = 1;
        Integer pagesize =10;

        if(size!=null){
            pagesize=size;
        }

        User user = (User) session.getAttribute("user");
        IPage<Blog> blogWithPage = blogService.getBlogWithPage(current, pagesize,keywords, user.getId());
        long current1 = blogWithPage.getCurrent();
        long pages = blogWithPage.getPages();
        long total = blogWithPage.getTotal();
        List<Blog> records = blogWithPage.getRecords();
        long size1 = blogWithPage.getSize();
        List<Source> sourcelist = sourceService.getAllSourceByState();

        List<Integer> allpages = new ArrayList<>();
        for (int i = 1; i < pages; i++) {
            allpages.add(i);
        }

        model.addAttribute("sourcelist", sourcelist);

        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("allpages", allpages);
        model.addAttribute("size", size1);
        model.addAttribute("total", total);
        model.addAttribute("recordlist", records);
        return "bloglist";
    }




    public  boolean deleteFile(String pathname){
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
        }
        return result;
    }

    @RequestMapping("/saveupdateblog")
    public String saveUpdateBlog(@RequestParam("title") String title,@RequestParam("oldtitle") String oldtitle, @RequestParam("keywords") String keywords, @RequestParam("sourceId") Integer sourceId, @RequestParam("pubtype") Integer pubtype, @RequestParam("labels") String[] labels, @RequestParam("radio") Integer radio, @RequestParam("textarea") String textarea, @RequestParam("time") String time, @RequestParam("level") Integer level, Model model, HttpSession session) throws IOException, ParseException {

        //保存博客路径
        String blogPath = session.getAttribute("blogsavepath").toString();


        Date date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").parse(time);
        Date date1 = new Date(System.currentTimeMillis());
        String blogId = session.getAttribute("updateBlogId").toString();
        Object sessionImgName = session.getAttribute("imgName");
        User user = (User) session.getAttribute("user");
        String imgfilename = "";
        if (sessionImgName == null) {

        } else {
            imgfilename = sessionImgName.toString();
        }

        Integer agree = 0;
        Integer comment = null;
        String intro = "";
        if (textarea.length() >= 200) {
            intro = textarea.substring(0, 200);
        } else {
            intro = textarea;
        }


        String categoryIds = "";

        for (int i = 0; i < labels.length; i++) {
            categoryIds = categoryIds + labels[i] + ",";
        }


        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setAgree(agree);
        blog.setComment(comment);
        blog.setTitle(title);
        blog.setImgs(imgfilename);
        blog.setCreateTime(date);
        blog.setUpdateTime(date1);
        blog.setIntro(intro);
        blog.setContent(textarea);
        blog.setLevel(level);
        blog.setState(1);
        blog.setTuijian(radio);
        blog.setSourceId(sourceId);
        blog.setUserId(user.getId());
        blog.setPubtypeId(pubtype);
        blog.setKeywords(keywords);
        blog.setTags(categoryIds);
        blog.setPath(blogPath);

        Integer result = blogService.updateBlogById(blog, blogId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date3 = new Date(System.currentTimeMillis());
        String time1 = formatter.format(date3);

        if (result == null) {

            model.addAttribute("msg", "博客添加失败！");
            List<Category> categoryList = categoryService.getAllCategoryByState();
            List<Pubtype> pubtypeList = pubtypeService.getAllPubtypeByState();
            List<Source> sourcelist = sourceService.getAllSourceByState();
            model.addAttribute("sourcelist", sourcelist);
            model.addAttribute("pubtypeList", pubtypeList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("time", time1);
            return "addblog";
        } else {
            //分页查询
            Integer current = 1;
            Integer size = 5;
            IPage<Blog> blogWithPage = blogService.getBlogWithPage(current, size, user.getId());
            long current1 = blogWithPage.getCurrent();
            long pages = blogWithPage.getPages();
            long total = blogWithPage.getTotal();
            List<Blog> records = blogWithPage.getRecords();
            long size1 = blogWithPage.getSize();
            List<Source> sourcelist = sourceService.getAllSourceByState();

            List<Integer> allpages=new ArrayList<>();
            for(int i=1;i<pages;i++){
                allpages.add(i);
            }

            model.addAttribute("sourcelist", sourcelist);

            model.addAttribute("current", current1);
            model.addAttribute("pre", current1 - 1);
            model.addAttribute("next", current1 + 1);
            model.addAttribute("pages", pages);
            model.addAttribute("allpages",allpages);
            model.addAttribute("size", size1);
            model.addAttribute("total", total);
            model.addAttribute("recordlist", records);
            return "bloglist";
        }

    }

    @RequestMapping("/updatepage")

    public String updatePage(@RequestParam("title") String title, @RequestParam("blogId") String blogId, @RequestParam("blogpath") String blogpath, @RequestParam("level") Integer level, HttpSession session, Model model) {

        session.setAttribute("updateBlogId", blogId);
        session.setAttribute("blogsavepath", blogpath);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        List<Category> categoryList = categoryService.getAllCategoryByState();
        List<Pubtype> pubtypeList = pubtypeService.getAllPubtypeByState();
        List<Source> sourcelist = sourceService.getAllSourceByState();
        Blog blog = blogService.getDetailedBlogById(blogId);
        model.addAttribute("sourcelist", sourcelist);
        model.addAttribute("pubtypeList", pubtypeList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("blogpath", blogpath);
        model.addAttribute("time", time);
        model.addAttribute("title", title);
        model.addAttribute("level", level);
        model.addAttribute("content",blog.getContent());
        return "updateblog";
    }

    @RequestMapping("/updateUploadImage")
    public void updateUploadImage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        ImgResData res = new ImgResData();


        //获取上传的文件集合
        Collection<Part> parts = request.getParts();
        //上传单个文件
        if (parts != null && parts.size() == 1) {
            //-----① 通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
            Part part = request.getPart("editormd-image-file");
            String header = part.getHeader("content-disposition");//获取请求头
            String imgFileName = getFileName(header);
            session.setAttribute("imgName", imgFileName);
            String url = GlobalConst.BASE_PATH + GlobalConst.IMG_FOLDER + imgFileName;
            //把文件写到指定路径
            part.write(url);

            res.setSuccess(1);
            res.setMessage("上传成功");
            StringBuffer requestURL = request.getRequestURL();
            String substring = requestURL.substring(0, requestURL.lastIndexOf("/"));
            res.setUrl(substring + "/updateImageShow?imgFileName=" + imgFileName);
        } else {
            res.setSuccess(0);
            res.setMessage("上传失败");
        }

        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(res));
        out.flush();
        out.close();
    }


    /**
     * 图片显示
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/updateImageShow")
    public void updateImageShow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();

        String imgFileName = request.getParameter("imgFileName");

        String imgFileNameWithPath = GlobalConst.BASE_PATH +GlobalConst.IMG_FOLDER + imgFileName;
        try (FileImageInputStream input = new FileImageInputStream(new File(imgFileNameWithPath));
             ByteArrayOutputStream output = new ByteArrayOutputStream();) {
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = input.read(buf)) != -1) {
                output.write(buf, 0, len);
            }
            byte[] data = output.toByteArray();
            out.write(data);
            out.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    @RequestMapping("/deletblog")

    public String deletBlog(@RequestParam("blogId") String blogId, HttpSession session, Model model) {

        Integer result = blogService.deletBlogById(blogId);
        User user = (User) session.getAttribute("user");
        if (result != null) {
            //分页查询
            Integer current = 1;
            Integer size = 5;
            IPage<Blog> blogWithPage = blogService.getBlogWithPage(current, size, user.getId());
            long current1 = blogWithPage.getCurrent();
            long pages = blogWithPage.getPages();
            long total = blogWithPage.getTotal();
            List<Blog> records = blogWithPage.getRecords();
            long size1 = blogWithPage.getSize();
            List<Source> sourcelist = sourceService.getAllSourceByState();

            List<Integer> allpages=new ArrayList<>();
            for(int i=1;i<pages;i++){
                allpages.add(i);
            }
            model.addAttribute("sourcelist", sourcelist);

            model.addAttribute("current", current1);
            model.addAttribute("pre", current1 - 1);
            model.addAttribute("next", current1 + 1);
            model.addAttribute("pages", pages);
            model.addAttribute("allpages",allpages);
            model.addAttribute("size", size1);
            model.addAttribute("total", total);
            model.addAttribute("recordlist", records);
            model.addAttribute("msg", "博客删除成功！");
            return "bloglist";
        } else {
            model.addAttribute("msg", "博客删除失败！");
            return "404";
        }


    }


    @PostMapping("/deletportionblog")
    public String deletPortionBlog(@RequestParam("checkbox") String [] checkbox, HttpSession session, Model model){
       Integer n=checkbox.length;
        for (int i = 0; i < checkbox.length; i++) {
            String id = checkbox[i];
            blogService.deletBlogById(id);
            n--;
        }
        User user = (User) session.getAttribute("user");
        //分页查询
       if(n<=0){
           //分页查询
           Integer current = 1;
           Integer size = 5;
           IPage<Blog> blogWithPage = blogService.getBlogWithPage(current, size, user.getId());
           long current1 = blogWithPage.getCurrent();
           long pages = blogWithPage.getPages();
           long total = blogWithPage.getTotal();
           List<Blog> records = blogWithPage.getRecords();
           long size1 = blogWithPage.getSize();
           List<Source> sourcelist = sourceService.getAllSourceByState();

           List<Integer> allpages=new ArrayList<>();
           for(int i=1;i<pages;i++){
               allpages.add(i);
           }
           model.addAttribute("sourcelist", sourcelist);

           model.addAttribute("current", current1);
           model.addAttribute("pre", current1 - 1);
           model.addAttribute("next", current1 + 1);
           model.addAttribute("pages", pages);
           model.addAttribute("allpages",allpages);
           model.addAttribute("size", size1);
           model.addAttribute("total", total);
           model.addAttribute("recordlist", records);
           model.addAttribute("msg", "博客删除成功！");
           return "bloglist";
       }else {
           model.addAttribute("msg", "博客删除失败！");
           return "admin";
       }
    }


    @RequestMapping("/myblogs")
    public String blogList(@RequestParam("current") Integer current, @RequestParam("size") Integer size, HttpSession session, Model model) {
        //分页查询
        Integer currentpage = 0;
        Integer currentsize = 0;
        if (current == null) {
            currentpage = 1;
        } else {
            currentpage = current;
        }

        if (size == null) {
            currentsize = 5;
        } else {
            currentsize = size;
        }
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        IPage<Blog> blogWithPage = blogService.getBlogWithPage(currentpage, currentsize, userId);
        long current1 = blogWithPage.getCurrent();
        long pages = blogWithPage.getPages();

        List<Integer> allpages=new ArrayList<>();
        for(int i=1;i<pages;i++){
            allpages.add(i);
        }
        long total = blogWithPage.getTotal();
        List<Blog> records = blogWithPage.getRecords();
        long size1 = blogWithPage.getSize();
        List<Source> sourcelist = sourceService.getAllSourceByState();

        model.addAttribute("sourcelist", sourcelist);
        model.addAttribute("current", current1);
        model.addAttribute("pre", current1 - 1);
        model.addAttribute("next", current1 + 1);
        model.addAttribute("pages", pages);
        model.addAttribute("allpages", allpages);
        model.addAttribute("size", size1);
        model.addAttribute("total", total);
        model.addAttribute("recordlist", records);

        return "bloglist";
    }


    @RequestMapping("/pages/{page}")
    public String test(@PathVariable("page") String page) {
        return page;
    }

    @RequestMapping("/addblog")
    public String addBlog(@RequestParam("page") String page, Model model, HttpServletRequest request, HttpSession session) {
        String blogId = System.currentTimeMillis() + "";
        session.setAttribute("blogId", blogId);
        User user = (User) session.getAttribute("user");
        String blogPath = user.getId() + "/" + blogId + "/"; //博客存储路径
        session.setAttribute("blogPath", blogPath);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        File file = new File(GlobalConst.BASE_PATH + GlobalConst.IMG_FOLDER);
        if (!file.exists()) { //如果路径不存在则创建
            file.mkdirs();
        }


        List<Category> categoryList = categoryService.getAllCategoryByState();
        List<Pubtype> pubtypeList = pubtypeService.getAllPubtypeByState();
        List<Source> sourcelist = sourceService.getAllSourceByState();
        model.addAttribute("sourcelist", sourcelist);
        model.addAttribute("pubtypeList", pubtypeList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("time", time);

        return page;

    }

    @PostMapping("/saveBlog")

    public String saveBlog(@RequestParam("title") String title, @RequestParam("keywords") String keywords, @RequestParam("sourceId") Integer sourceId, @RequestParam("pubtype") Integer pubtype, @RequestParam("labels") String[] labels, @RequestParam("radio") Integer radio, @RequestParam("textarea") String textarea, @RequestParam("time") String time, @RequestParam("level") Integer level, Model model, HttpSession session) throws IOException, ParseException {

        //保存博客
        String blogPath = GlobalConst.BASE_PATH+GlobalConst.IMG_FOLDER ;
        Path htmlPath = Paths.get(blogPath);

        if (!Files.exists(htmlPath)) {
            Files.createDirectories(htmlPath);
        }


        Date date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").parse(time);
        Date date1 = new Date(System.currentTimeMillis());
        Object blogId = session.getAttribute("blogId");
        Object sessionImgName = session.getAttribute("imgName");
        User user = (User) session.getAttribute("user");
        String imgfilename = "";
        if (sessionImgName == null) {

        } else {
            imgfilename = sessionImgName.toString();
        }

        Integer agree = 0;
       Integer comment =null;
        String intro = "";
        if (textarea.length() >= 250) {
            intro = textarea.substring(0, 250);
        } else {
            intro = textarea;
        }


        String categoryIds = "";

        for (int i = 0; i < labels.length; i++) {
            categoryIds = categoryIds + labels[i] + ",";
        }


        Blog blog = new Blog();
        blog.setId(blogId.toString());
        blog.setAgree(agree);
        blog.setComment(comment);
        blog.setTitle(title);
        blog.setImgs(imgfilename);
        blog.setCreateTime(date);
        blog.setUpdateTime(date1);
        blog.setIntro(intro);
        blog.setContent(textarea);
        blog.setLevel(level);
        blog.setState(1);
        blog.setTuijian(radio);
        blog.setSourceId(sourceId);
        blog.setUserId(user.getId());
        blog.setPubtypeId(pubtype);
        blog.setKeywords(keywords);
        blog.setTags(categoryIds);
        blog.setPath(blogPath);

        Integer result = blogService.addNewBlog(blog);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date3 = new Date(System.currentTimeMillis());
        String time1 = formatter.format(date3);

        if (result == null) {

            model.addAttribute("msg", "博客添加失败！");
            List<Category> categoryList = categoryService.getAllCategoryByState();
            List<Pubtype> pubtypeList = pubtypeService.getAllPubtypeByState();
            List<Source> sourcelist = sourceService.getAllSourceByState();
            model.addAttribute("sourcelist", sourcelist);
            model.addAttribute("pubtypeList", pubtypeList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("time", time1);
            return "addblog";
        } else {
            //分页查询
            Integer current = 1;
            Integer size = 5;
            IPage<Blog> blogWithPage = blogService.getBlogWithPage(current, size, user.getId());
            long current1 = blogWithPage.getCurrent();
            long pages = blogWithPage.getPages();
            long total = blogWithPage.getTotal();
            List<Blog> records = blogWithPage.getRecords();
            long size1 = blogWithPage.getSize();
            List<Source> sourcelist = sourceService.getAllSourceByState();
            List<Integer> allpages=new ArrayList<>();
            for(int i=1;i<pages;i++){
                allpages.add(i);
            }

            model.addAttribute("sourcelist", sourcelist);

            model.addAttribute("current", current1);
            model.addAttribute("pre", current1 - 1);
            model.addAttribute("next", current1 + 1);
            model.addAttribute("pages", pages);
            model.addAttribute("allpages", allpages);
            model.addAttribute("size", size1);
            model.addAttribute("total", total);
            model.addAttribute("recordlist", records);
            return "bloglist";
        }

    }


    @RequestMapping("/imageUpload")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        ImgResData res = new ImgResData();

        //获取上传的文件集合
        Collection<Part> parts = request.getParts();
        //上传单个文件
        if (parts != null && parts.size() == 1) {
            //-----① 通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
            Part part = request.getPart("editormd-image-file");
            String header = part.getHeader("content-disposition");//获取请求头
            String imgFileName = getFileName(header);
            session.setAttribute("imgName", imgFileName);
            String url = GlobalConst.BASE_PATH +  GlobalConst.IMG_FOLDER + imgFileName;
            //把文件写到指定路径
            part.write(url);

            res.setSuccess(1);
            res.setMessage("上传成功");
            StringBuffer requestURL = request.getRequestURL();
            String substring = requestURL.substring(0, requestURL.lastIndexOf("/"));
            res.setUrl(substring + "/imageShow?imgFileName=" + imgFileName);
        } else {
            res.setSuccess(0);
            res.setMessage("上传失败");
        }

        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(res));
        out.flush();
        out.close();
    }


    /**
     * 图片回显
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/imageShow")
    public void imageShow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        String imgFileName = request.getParameter("imgFileName");
        String imgFileNameWithPath = GlobalConst.BASE_PATH +  GlobalConst.IMG_FOLDER + imgFileName;
        try (FileImageInputStream input = new FileImageInputStream(new File(imgFileNameWithPath));
             ByteArrayOutputStream output = new ByteArrayOutputStream();) {
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = input.read(buf)) != -1) {
                output.write(buf, 0, len);
            }
            byte[] data = output.toByteArray();
            out.write(data);
            out.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 根据请求头解析出文件名
     *
     * @param header 请求头
     * @return 文件名
     */
    public String getFileName(String header) {
        String[] temp = header.split(";")[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = temp[1].substring(temp[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        return UUID.randomUUID().toString().replaceAll("-", "").substring(3, 9) + suffix;
    }


}
