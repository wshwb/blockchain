package com.gala.blockchain.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gala.blockchain.common.utils.Log;
import com.gala.blockchain.common.utils.Result;
import com.gala.blockchain.entity.WorksHardcopyInfo;
import com.gala.blockchain.entity.WorksInfo;
import com.gala.blockchain.mapper.WorksInfoMapper;
import com.gala.blockchain.service.IWorksInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: create by Christina
 * @date:2020/7/23
 * @time: 8:11
 */
@RestController
@RequestMapping("/worksrecommend")
public class WorksRcommendController {

    @Autowired
    IWorksInfoService worksService;

    @Autowired
    private WorksInfoMapper worksMapper;


    @Log("获取全部推荐作品")
    @ResponseBody
    @PostMapping("/all")
    public Result all (@RequestParam Integer pageNo,@RequestParam Integer pageSize){
        Page<WorksInfo> page = new Page<WorksInfo>(pageNo, pageSize);
        //联合查询
        Map<String ,Object> data = new HashMap<>();
        List<Map<String ,Object>> list =  worksMapper.dyrecommendList(page);
        data.put("records",list);
        //分页信息
        data.put("total",page.getTotal());
        data.put("size",page.getSize());
        data.put("current",page.getCurrent());
        data.put("pages",page.getPages());
        return Result.datas(data);
    }

    @Log("推荐作品增删")
    @ResponseBody
    @PostMapping("/recommend")
    public Result recommend (@RequestParam Integer worksId,@RequestParam Integer recommend){
        //根据worksId获取作品
        QueryWrapper<WorksInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("workId",worksId);
        WorksInfo worksInfo = worksService.getOne(queryWrapper);
        if(worksInfo == null){
            return Result.error("无此作品");
        }else {
            worksInfo.setRecommend(recommend);
            worksService.update(worksInfo,queryWrapper);
            return Result.build();
        }
    }


}
