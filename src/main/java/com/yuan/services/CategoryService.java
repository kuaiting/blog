package com.yuan.services;

import com.yuan.bean.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 获取文章类型
     * @return Category
     */
    List<Category> getAllCategoryByState();
}
