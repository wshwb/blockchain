package com.gala.blockchain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by brayden on 2020/7/14 21:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "works_verify_info")
public class WorksVerifyInfo implements Serializable {

    private static final long serialVersionUID = 6047085625636768358L;
    @TableId(value = "Id")
    private Integer d;

    @TableField(value = "worksId")
    private String worksId;

    @TableField(value = "worksName")
    private String worksName;

    @TableField(value = "type")
    private Integer type;

    @TableField(value = "user")
    private String user;

    @TableField(value = "worksAbstract")
    private String worksAbstract;

    @TableField(value = "uploadTime")
    private Date uploadTime;

    @TableField(value = "worksPathOrigin")
    private String worksPathOrigin;
}
