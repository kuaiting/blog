package com.yuan.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bean.Source;
import com.yuan.mapper.SourceMapper;
import com.yuan.services.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl  implements SourceService {
    @Autowired
    private SourceMapper sourceMapper;
    @Override
    public List<Source>  getAllSourceByState() {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        List<Source> sources = sourceMapper.selectList(wrapper);
        return sources;
    }
}
