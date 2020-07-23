package com.gala.blockchain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gala.blockchain.common.utils.Result;
import com.gala.blockchain.entity.SysLog;
import com.gala.blockchain.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gala
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController {


    @Autowired
    ISysLogService sysLogService;
    @ResponseBody
    @PostMapping("/all")
    public Result allLog()  {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<SysLog> list = sysLogService.list(queryWrapper);
        return  Result.data("list",list);
    }
}
