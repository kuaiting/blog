package com.yuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bean.Town;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component

public interface TownMapper extends BaseMapper<Town> {
}
