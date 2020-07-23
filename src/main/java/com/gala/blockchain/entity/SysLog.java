package com.gala.blockchain.entity;

import java.time.LocalDateTime;
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
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private Integer id;
    /**
     * 操作人
     */
    @TableField(value = "author_name")
    private String authorName;

    @TableField(value = "author_id")
    private Integer authorId;

    /**
     * 操作
     */
    @TableField(value = "doing")
    private String doing;

    @TableField(value = "create_time")
    private Date createTime;


}
