package com.yuan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.*;
import com.yuan.services.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserSevices userSevices;

    @Autowired
    private TownService townService;

    @Autowired
    private ProviceService proviceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/pages")
    public String topage(@RequestParam("page") String page) {
        return page;
    }

    @RequestMapping("/toadmin")
    public String toAdmin(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "admin";
        } else {
            model.addAttribute("msg", "您还没有登录！");
            return "login";

        }


    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {

        session.removeAttribute("user");

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

    @PostMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("code") String code, HttpSession session, Model model) throws IOException {
        User user1 = new User();
        user1.setLoginName(name);
        user1.setPassword(password);
        User user = null;
        String code0 = session.getAttribute("code").toString();
        if (!code.equals(code0)) {
            model.addAttribute("验证码不匹配,请重试！！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        try {
            subject.login(token);
            user = userSevices.getUserByLoginNameAndPassword(user1);

        } catch (Exception e) {
            model.addAttribute("msg", "用户名或密码不正确,请重试!!");
            return "login";
        }


        if (user != null) {
            session.setAttribute("user", user);
            return "admin";
        } else {
            return "login";
        }


    }


    @RequestMapping("/unauth")
    @ResponseBody
    public String unauth() {
        return "未授权无法访问此页面！";
    }


    @GetMapping("/touser")
    public String toUserPage(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        User user1 = (User) user;
        Integer townId = user1.getTownId();
        Town town = townService.getTown(townId);
        Integer cityId = town.getCityId();
        City city = cityService.getCityById(cityId);
        Integer provinceId = city.getProvinceId();
        Province provice = proviceService.getProviceById(provinceId);

        String address = provice.getName() + "-" + city.getName() + "-" + town.getName();
        session.setAttribute("address", address);
        session.setAttribute("townId", townId);
        model.addAttribute("user", user1);
        model.addAttribute("address", address);
        return "user";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Integer id, HttpSession session, Model model) {
        User user = userSevices.getUserById(id);
        Object address = session.getAttribute("address");
        String s = address.toString();
        model.addAttribute("getaddress", s);
        model.addAttribute("user", user);
        return "updateinfo";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("loginName") String loginName, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("role") String role, @RequestParam("tel") String tel, @RequestParam("age") Integer age, @RequestParam("town") String town, @RequestParam("gender") String gender, @RequestParam("birth") String birth, @RequestParam("email") String email, @RequestParam("intro") String intro, Model model) throws ParseException {


        String[] split = town.split("-");
        String s = split[2];
        Town townname = townService.getTownByName(s);
        Integer townId = townname.getId();

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(birth);
        Date date1 = new Date();
        int n = "男".equals(gender) ? 1 : 0;
        User user = new User();
        user.setRole(role);
        user.setPassword(password);
        user.setName(name);
        user.setTel(tel);
        user.setGender(n);
        user.setAge(age);
        user.setIntro(intro);
        user.setEmail(email);
        user.setBirth(date);
        user.setTownId(townId);
        user.setLoginName(loginName);
        user.setCreateTime(date1);
        user.setState(1);
        user.setPerms("all");
        Integer integer = userSevices.addUser(user);
        if (integer == 1) {

            return "login";
        } else {
            model.addAttribute("msg", "注册失败！");
            return "register";
        }


    }

    @PostMapping("/uploaduser")

    public String updateUser(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("role") String role, @RequestParam("age") Integer age, @RequestParam("gender") String gender, @RequestParam("birth") String birth, @RequestParam("email") String email, @RequestParam("intro") String intro, HttpSession session, Model model) throws ParseException {

        String Id = session.getAttribute("townId").toString();
        Integer townId = Integer.parseInt(Id);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(birth);
        int n = "男".equals(gender) ? 1 : 0;
        User user = new User();
        user.setRole(role);
        user.setPassword(password);
        user.setName(name);
        user.setGender(n);
        user.setAge(age);
        user.setIntro(intro);
        user.setEmail(email);
        user.setBirth(date);
        user.setTownId(townId);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //更新条件
        wrapper.eq("id", id);

        Integer res = userSevices.updateUserWrapper(user, wrapper);
        if (res == 1) {
            User userById = userSevices.getUserById(id);
            Integer townId1 = userById.getTownId();
            Town town = townService.getTown(townId);
            Integer cityId = town.getCityId();
            City city = cityService.getCityById(cityId);
            Integer provinceId = city.getProvinceId();
            Province provice = proviceService.getProviceById(provinceId);

            String address = provice.getName() + "-" + city.getName() + "-" + town.getName();
            session.setAttribute("address", address);
            session.setAttribute("townId", townId1);
            model.addAttribute("address", address);
            model.addAttribute("user", userById);
            return "user";
        } else {
            User user1 = userSevices.getUserById(id);
            Object address = session.getAttribute("address");
            String s = address.toString();
            model.addAttribute("getaddress", s);
            model.addAttribute("user", user1);
            model.addAttribute("msg", "修改用户信息失败");
            return "updateinfo";
        }


    }

}
