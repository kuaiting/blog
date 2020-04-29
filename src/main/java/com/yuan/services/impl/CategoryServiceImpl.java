package com.yuan.services.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bean.Category;
import com.yuan.mapper.CategoryMapper;
import com.yuan.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public  List<Category> getAllCategoryByState() {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        List<Category> categories = categoryMapper.selectList(wrapper);
        return categories;
    }
}
