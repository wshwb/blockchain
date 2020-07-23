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
 * Created by brayden on 2020/7/14 21:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "works_assess_info")
public class WorksAssessInfo implements Serializable {

    private static final long serialVersionUID = 4673175078817023069L;
    @TableId(value = "Id")
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
