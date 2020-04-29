package com.yuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bean.Books;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BooksMapper extends BaseMapper<Books> {
}
