package com.yuan.services.impl;

import com.yuan.bean.Province;
import com.yuan.mapper.ProvinceMapper;
import com.yuan.services.ProviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProviceService {
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public Province getProviceById(Integer id) {
        Province province = provinceMapper.selectById(id);

        return province;
    }
}
