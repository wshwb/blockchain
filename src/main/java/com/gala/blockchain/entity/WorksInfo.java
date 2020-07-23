package com.gala.blockchain.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author gala
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "works_info")
public class WorksInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "Id")
    private Integer id;

    @TableField(value = "workId")
    private String workId;

    @TableField(value = "worksName")
    private String worksName;

    @TableField(value = "type")
    private Integer type;

    @TableField(value = "user")
    private String user;

    @TableField(value = "workAbstract")
    private String workAbstract;

    @TableField(value = "state")
    private Integer state;

    @TableField(value = "uploadTime")
    private Date uploadTime;

    @TableField(value = "verifyTime")
    private Date verifyTime;

    @TableField(value = "bchTime")
    private Date bchTime;

    @TableField(value = "worksPath")
    private String worksPath;

    @TableField(value = "worksPathOrigin")
    private String worksPathOrigin;

    @TableField(value = "manager")
    private String manager;

    @TableField(value = "bchHash")
    private String bchHash;

    @TableField(value = "bchHeight")
    private Integer bchHeight;

    @TableField(value = "recommend")
    private Integer recommend;

    @TableField(value = "recommendTime")
    private Date recommendTime;


}
