package com.yuan.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO)
private  Integer id;
private String name;
private  String loginName;
private  String password;
private  String tel;
private  Integer gender;
private  Integer age;
private Date birth;
private String img;
private Integer townId;
private String email;
private String intro;
private String perms;
private String role;
private Integer state;
private Date createTime;
private Date updateTime;


}
