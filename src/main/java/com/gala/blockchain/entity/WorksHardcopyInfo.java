package com.gala.blockchain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by brayden on 2020/7/14 21:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "works_hardcopy_info")
public class WorksHardcopyInfo implements Serializable {

    private static final long serialVersionUID = -2478616173133441945L;
    @TableField(value = "Id")
    private Integer id;

    @TableField(value = "worksId")
    private String worksId;

    @TableField(value = "user")
    private String user;

    @TableField(value = "requestTime")
    private Date requestTime;

    @TableField(value = "deal")
    private Integer deal;
}
