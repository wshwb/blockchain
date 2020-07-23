package com.gala.blockchain.entity;


import java.io.Serializable;
import java.util.Date;

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
@TableName(value = "policy_info")
public class PolicyInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private Integer id;
    /**
     * 标题
     */
    @TableField(value = "policyTitle")
    private String policyTitle;

    /**
     * 失效日期
     */
    @TableField(value = "expireDate")
    private Date expireDate;

    /**
     * 连接
     */
    @TableField(value = "policyUrl")
    private String policyUrl;

    /**
     * 上传日期
     */
    @TableField(value = "uploadTime")
    private Date uploadTime;


}
