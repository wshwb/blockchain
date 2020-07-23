package com.gala.blockchain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gala.blockchain.common.utils.Log;
import com.gala.blockchain.common.utils.Result;
import com.gala.blockchain.entity.*;

import com.gala.blockchain.mapper.TagInfoMapper;
import com.gala.blockchain.mapper.WorksInfoMapper;
import com.gala.blockchain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gala
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/works")
public class WorksController {

    @Autowired
    IWorksInfoService worksService;

    @Autowired
    IWorksVerifyInfoService worksVerifyInfoService;

    @Autowired
    IWorksAssessInfoService worksAssessInfoService;

    @Autowired
    IWorksHardcopyInfoService worksHardcopyInfoService;

    @Autowired
    IUserService userService;

    @Autowired
    ITagInfoService tagInfoService;

    @Autowired
    private TagInfoMapper tagInfoMapper;

    @Autowired
    private WorksInfoMapper worksMapper;

    @Log("搜索作品")
    @ResponseBody
    @PostMapping("/all")
    public Result allWorks(@RequestParam (required=false,defaultValue="")String key,
                         @RequestParam (required=false,defaultValue="") String searchType,
                         @RequestParam Integer pageNo,
                         @RequestParam Integer pageSize){
        Page<WorksInfo> page = new Page<WorksInfo>(pageNo, pageSize);
        Map<String ,Object> data = new HashMap<>();
        if (key.isEmpty()) {
            //联合查询
            List<Map<String ,Object>> list1 =  worksMapper.dygetList(page);
            data.put("records",list1);
        } else {
            if (searchType.equals("worksName") ) {
                //联合查询
                List<Map<String ,Object>> list2 =  worksMapper.selectWorksNameList(page,key);
                data.put("records",list2);
            } else
//                if(searchType.equals("userName"))
                {
                //联合查询
                List<Map<String ,Object>> list3 =  worksMapper.selectUserNameList(page,key);
                data.put("records",list3);
            }
        }
        //分页信息
        data.put("total",page.getTotal());
        data.put("size",page.getSize());
        data.put("current",page.getCurrent());
        data.put("pages",page.getPages());
        return Result.datas(data);
    }

    @Log("作品分类")
    @ResponseBody
    @PostMapping("/typeselect")
    public Object typeselect (@RequestParam Integer type,
                              @RequestParam Integer pageNo,
                              @RequestParam Integer pageSize){
        Page<WorksInfo> page = new Page<WorksInfo>(pageNo, pageSize);
        //联合查询
        List<Map<String ,Object>> list =  worksMapper.dyGetUserNameList(page,type);
        Map<String ,Object> data = new HashMap<>();
        data.put("records",list);
        //分页信息
        data.put("total",page.getTotal());
        data.put("size",page.getSize());
        data.put("current",page.getCurrent());
        data.put("pages",page.getPages());
        return Result.datas(data);
    }

    @Log("标签分类")
    @ResponseBody
    @PostMapping("/tagselect")
    public Result tagselect (@RequestParam Integer type,
                             @RequestParam (required=false,defaultValue="")Integer tagId,
                              @RequestParam Integer pageNo,
                              @RequestParam Integer pageSize){
        Page<WorksInfo> page = new Page<WorksInfo>(pageNo,pageSize);
        if(tagId == null){
            List<Map<String, Object>> list1 = worksMapper.allGettagIdList(type);
            Map<String ,Object> data1 = new HashMap<>();
            data1.put("records",list1);
            return Result.datas(data1);
        }else {
            List<Map<String, Object>> list2 = worksMapper.dyGettagIdList(type, tagId);
            Map<String ,Object> data2 = new HashMap<>();
            data2.put("records",list2);
            return Result.datas(data2);
        }

    }

    @ResponseBody
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id){
        WorksInfo worksInfo = worksService.getById(id);
        Map<String,Object> data = new HashMap<>();
        data.put("works", worksInfo);
        return  Result.datas(data);
    }

    @Log("审核作品")
    @ResponseBody
    @PostMapping("/audit")
    public Result Audit(@RequestParam Integer id,@RequestParam Integer audit){
        WorksInfo worksInfo = worksService.getById(id);
//        worksInfo.setIsAudit(audit);
        worksService.updateById(worksInfo);
        return  Result.build();
    }

    @Log("下载图片")
    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadPhoto(MultipartFile photo, HttpServletRequest request) throws IOException {
        Map<String, String> ret = new HashMap<String, String>();
        if (photo == null) {
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if (photo.getSize() > 1024 * 1024 * 10) {
            ret.put("type", "error");
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }

        //todo 更换为前端项目的img路径下
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
        String nyr = dateFormat.format(new Date());
        String savePath = "/usr/local/nginx/html/img/";
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdirs();
        }
        String filename = new Date().getTime() + "." + suffix;
        try {
            //将文件保存指定目录
            photo.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "上传图片成功！");
        ret.put("filepath", "img/");
        ret.put("filename", filename);
        return ret;
    }

    @Log("添加作品")
    @ResponseBody
    @PostMapping("/add")
    public Result add(@RequestParam String user,
                      @RequestParam String worksName,
                      @RequestParam String workAbstract,
                      @RequestParam Integer type,
                      @RequestParam Integer tagId,
                      @RequestParam String worksPath,
                      @RequestParam String worksPathOrigin){
        WorksInfo worksInfo = new WorksInfo();
        WorksTagInfo worksTagInfo = new WorksTagInfo();
        //获取一个时间戳
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        //设置作品Id:由用户手机号后4位+时间戳后9位组成
        String workId= user.substring(0,3) + dateStr.substring(dateStr.length() - 9);
        //worksinfo添加
        worksInfo.setUser(user);
        worksInfo.setWorksName(worksName);
        worksInfo.setWorkAbstract(workAbstract);
        worksInfo.setType(type);
        worksInfo.setWorkId(workId);

        //workstaginfo添加
        worksTagInfo.setWorksId(workId);
        worksTagInfo.setTagId(tagId);
        
        worksService.save(worksInfo);
        return  Result.build();
    }

    @Log("首页信息")
    @ResponseBody
    @PostMapping("/indexData")
    public Result indexData(){
        int all = worksService.count();

        int verify = worksVerifyInfoService.count();

        QueryWrapper<WorksInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("state",2);
        int confirm = worksService.count(queryWrapper1);

        QueryWrapper<WorksAssessInfo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("deal",0);
        int assess = worksAssessInfoService.count(queryWrapper2);

        QueryWrapper<WorksHardcopyInfo> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("deal",0);
        int hardcopy = worksHardcopyInfoService.count(queryWrapper3);

        Map<String ,Object> data = new HashMap<>();
        data.put("all",all);
        data.put("confirm",confirm);
        data.put("verify",verify);
        data.put("assess",assess);
        data.put("hardcopy",hardcopy);
        return  Result.datas(data);
    }

    @Log("请求处理")
    @ResponseBody
    @PostMapping("/deal")
    public Result deal(@RequestParam Integer id,@RequestParam Integer dealType){
        WorksInfo worksInfo = worksService.getById(id);
        if(dealType == 1){
//            worksInfo.setEvaProcessing(1);
        }else {
//            worksInfo.setCertificateProcessing(1);
        }
        worksService.updateById(worksInfo);
        return  Result.info("处理成功");
    }

}
