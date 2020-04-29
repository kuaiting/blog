package com.yuan.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_comment")
public class Comments {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String msg;
    private String blogId;
    private Integer state;
    private Date createTime;
    private Date updateTime;
}
