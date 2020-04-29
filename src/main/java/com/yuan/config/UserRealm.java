package com.yuan.config;

import com.yuan.bean.User;

import com.yuan.services.UserSevices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {
    @Autowired

    private UserSevices userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //此处才做授权工作
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前的用户(获取认证时查询出的用户信息 即是：user)
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());
        info.addRole(currentUser.getRole());

        return info;
    }

    //认证
    @Override

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        User user = userService.checkUserByName(usertoken.getUsername());
        if (user == null) {
            return null;
        }

        //密码认证 可做加密
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
