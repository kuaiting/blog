package com.yuan.services;

import com.yuan.bean.Town;

public interface TownService {
    Town getTown(Integer id) ;

    Town getTownByName(String name);
}
