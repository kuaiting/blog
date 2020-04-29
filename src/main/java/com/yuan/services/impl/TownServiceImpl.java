package com.yuan.services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bean.Town;
import com.yuan.mapper.TownMapper;
import com.yuan.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {
    @Autowired
    private TownMapper townMapper;
    @Override
    public Town getTown(Integer id) {

        Town town = townMapper.selectById(id);

        return town;
    }

    @Override
    public Town getTownByName(String name) {
        QueryWrapper<Town> wrapper= new QueryWrapper<>();
        wrapper.eq("name",name);
        Town town = townMapper.selectOne(wrapper);
        return town;
    }
}
