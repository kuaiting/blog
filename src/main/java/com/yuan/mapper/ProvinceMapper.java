package com.yuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yuan.bean.Province;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProvinceMapper extends BaseMapper<Province> {
}
