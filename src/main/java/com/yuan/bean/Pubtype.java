package com.yuan.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_pubtype")
public class Pubtype {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer level;
    private Integer state;

}
