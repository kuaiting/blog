package com.yuan.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryMapper extends BaseMapper<Category> {
}
