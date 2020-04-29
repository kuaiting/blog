package com.yuan.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bean.User;
import com.yuan.mapper.UserMapper;
import com.yuan.services.UserSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServicesImpl implements UserSevices {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByLoginNameAndPassword(User user) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("login_name", user.getLoginName());
        columnMap.put("password", user.getPassword());
        List<User> users = userMapper.selectByMap(columnMap);

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }


    }


    @Override
    public Integer addUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }


    @Override
    public Integer updateUserById(User user) {
        int res = userMapper.updateById(user);
        return res;
    }

    @Override
    public Integer updateUserWrapper(User user, QueryWrapper wrapper) {
        int res = userMapper.update(user, wrapper);
        return res;
    }

    @Override
    public Integer deletUserById(Integer id) {
        int res = userMapper.deleteById(id);
        return res;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public User checkUserByName(String username) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public List<User> getAllUser() {

        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        List<User> users = userMapper.selectList(wrapper);
        return users;
    }
}
