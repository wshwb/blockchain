package com.gala.blockchain.controller;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gala.blockchain.common.utils.Log;
import com.gala.blockchain.common.utils.Result;
import com.gala.blockchain.entity.PolicyInfo;
import com.gala.blockchain.entity.WorksVerifyInfo;
import com.gala.blockchain.mapper.WorksVerifyInfoMapper;
import com.gala.blockchain.service.IPolicyService;
import com.gala.blockchain.service.IWorksVerifyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: create by Christina
 * @date:2020/7/21
 * @time: 14:50
 */
@RestController
@RequestMapping("/worksverify")
public class WorksVerifyController {

    @Autowired
    IWorksVerifyInfoService worksVerifyService;

    @Autowired
    WorksVerifyInfoMapper worksVerifyMapper;

    @Log("获取全部待审核作品")
    @ResponseBody
    @PostMapping("/all")
    public Object allVerify(@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        Page<WorksVerifyInfo> page = new Page<WorksVerifyInfo>(pageNo, pageSize);
        QueryWrapper<WorksVerifyInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("worksId","worksName","user","uploadTime");
        return worksVerifyService.page(page, queryWrapper);
    }
}
