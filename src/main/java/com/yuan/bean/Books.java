package com.yuan.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_books")
public class Books {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String book;
    private  String  blogId;
    private Integer userId;
    private Integer state;
    private Date createTime;
    private Date updateTime;

}
