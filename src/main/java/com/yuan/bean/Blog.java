package com.yuan.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_blog")
public class Blog {
    //这是id
    private String  id;
    private String title;
    private String path;
    private String tags;
    private String imgs;
    private String intro;
    private String content;
    private Integer agree;
    //评论
    private Integer comment;
    private Integer level;
    //推荐
    private Integer tuijian;
    //文章类型
    private Integer sourceId;

    //发布形式
    private Integer pubtypeId;
    private String keywords;
    private Integer userId;
    private Integer state;
    private Date createTime;
    private Date updateTime;
}
