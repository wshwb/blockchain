package com.gala.blockchain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: create by Christina
 * @date:2020/7/15
 * @time: 10:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "works_tag_info")
public class WorksTagInfo implements Serializable {

    private static final long serialVersionUID = -6983636221340209428L;
    @TableId(value = "Id")
    private Integer id;

    @TableField(value = "worksId")
    private String worksId;

    @TableField(value = "tagId")
    private Integer tagId;
}
