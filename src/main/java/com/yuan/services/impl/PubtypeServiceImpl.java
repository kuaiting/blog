package com.yuan.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bean.Pubtype;
import com.yuan.mapper.PubtypeMapper;
import com.yuan.services.PubtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PubtypeServiceImpl implements PubtypeService {

   @Autowired
   private PubtypeMapper pubtypeMapper;
    @Override
    public List<Pubtype> getAllPubtypeByState() {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        List<Pubtype> pubtypes = pubtypeMapper.selectList(wrapper);
        return pubtypes;
    }
}
