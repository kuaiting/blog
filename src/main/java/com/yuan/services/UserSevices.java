package com.yuan.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bean.User;

import java.util.List;

public interface UserSevices {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 通过用户名密码查询用户信息
     *
     * @param user
     * @return
     */
    User getUserByLoginNameAndPassword(User user);





    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Integer addUser(User user);



    /**
     * 根据ID做更新
     *
     * @param user
     * @return
     */
    Integer updateUserById(User user);

    /**
     * 根据条件做更新
     *
     * @param user
     * @param wrapper
     * @return
     */
    Integer updateUserWrapper(User user, QueryWrapper wrapper);

    /**
     * 通过Id删除用户
     *
     * @param id
     * @return
     */
    Integer deletUserById(Integer id);

    /**
     * 通过用户ID查询用户信息
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    User checkUserByName(String username);
}
