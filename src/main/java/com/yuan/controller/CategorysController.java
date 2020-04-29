package com.yuan.controller;

import com.yuan.bean.Category;
import com.yuan.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategorysController {

    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/getCategory")
    public String getCategory(Model model){
        List<Category> categorys = categoryService.getAllCategoryByState();
        model.addAttribute("categories",categorys);
        return "index";
    }
}
