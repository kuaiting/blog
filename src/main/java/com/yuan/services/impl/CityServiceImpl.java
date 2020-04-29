package com.yuan.services.impl;

import com.yuan.bean.City;
import com.yuan.mapper.CityMapper;
import com.yuan.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;
    @Override
    public City getCityById(Integer id) {
        City city = cityMapper.selectById(id);
        return city;
    }
}
