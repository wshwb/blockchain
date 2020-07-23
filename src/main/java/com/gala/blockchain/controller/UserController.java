package com.gala.blockchain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gala.blockchain.common.utils.Log;
import com.gala.blockchain.common.utils.Result;
import com.gala.blockchain.entity.UserInfo;
import com.gala.blockchain.mapper.UserMapper;
import com.gala.blockchain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gala
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/author")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Log("作者搜索")
    @ResponseBody
    @PostMapping("/all")
    public Object allAuthor(@RequestParam(required=false,defaultValue="")String key,
                            @RequestParam(required=false,defaultValue="userName")String searchType,
                            @RequestParam Integer pageNo,
                            @RequestParam Integer pageSize) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        if (key == null) {
            queryWrapper.isNotNull("id");
            //分页
            Page<UserInfo> page = new Page<UserInfo>(pageNo, pageSize);
            return userService.page(page, queryWrapper);
        } else {
            if (searchType.equals("id") || searchType.equals("gender") || searchType.equals("worksTotal") || searchType.equals("worksInBch")) {
                queryWrapper.eq(searchType, Integer.valueOf(key));
                //分页
                Page<UserInfo> page = new Page<UserInfo>(pageNo, pageSize);
                return userService.page(page, queryWrapper);
            } else {
                queryWrapper.like(searchType, key);
                //分页
                Page<UserInfo> page = new Page<UserInfo>(pageNo, pageSize);
                return userService.page(page, queryWrapper);
            }
        }
    }

    @Log("添加作者")
    @ResponseBody
    @PostMapping("/add")
    public Result add(@RequestParam String user,
                      @RequestParam String userName,
                      @RequestParam Integer gender,
                      @RequestParam String workUnit,
                      @RequestParam (required=false,defaultValue="2020-07-20")String birth, //创建日期
                      @RequestParam (required=false,defaultValue="null")String email) throws ParseException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setUserName(userName);
        userInfo.setGender(gender);
        userInfo.setWorkUnit(workUnit);
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(birth);
        userInfo.setBirth(date);
        userInfo.setEmail(email);

        userService.save(userInfo);
        return  Result.build();
    }
    }
