package com.gala.blockchain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gala.blockchain.entity.WorksAssessInfo;
import com.gala.blockchain.entity.WorksInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gala
 * @since 2020-06-04
 */
public interface WorksInfoMapper extends BaseMapper<WorksInfo> {
    //select
    @Select("SELECT *" +
            "FROM works_info a " +
            "INNER JOIN user_info b " +
            "ON a.user = b.user " )
    List<Map<String ,Object>> dygetList(Page<WorksInfo> page);

    @Select("SELECT *" +
            "FROM works_info a " +
            "INNER JOIN user_info b " +
            "ON a.user = b.user " +
            "WHERE a.worksName = #{worksName}" )
    List<Map<String ,Object>> selectWorksNameList(Page<WorksInfo> page,String worksName);

    @Select("SELECT *" +
            "FROM works_info a " +
            "INNER JOIN user_info b " +
            "ON a.user = b.user " +
            "WHERE b.userName = #{userName}" )
    List<Map<String ,Object>> selectUserNameList(Page<WorksInfo> page,String userName);


    //typeselect
    @Select("SELECT *" +
            "FROM works_info a " +
            "INNER JOIN user_info b " +
            "ON a.user = b.user " +
            "WHERE a.type = #{type}")
    List<Map<String ,Object>> dyGetUserNameList(Page<WorksInfo> page,Integer type);

    //tagselect
    @Select("SELECT * " +
            "FROM works_tag_info a " +
            "INNER JOIN works_info b " +
            "ON a.worksId = b.workId " +
            "INNER JOIN user_info c " +
            "ON b.user = c.user " +
            "WHERE b.type = #{type}" )
//    List<Map<String ,Object>> allGettagIdList(Page<WorksInfo> page,Integer type);
    List<Map<String ,Object>> allGettagIdList(Integer type);

    @Select("SELECT * " +
           "FROM works_tag_info a " +
           "INNER JOIN works_info b " +
           "ON a.worksId = b.workId " +
            "INNER JOIN user_info c " +
            "ON b.user = c.user " +
           "WHERE a.tagId = #{tagId} AND b.type = #{type}" )
//    List<Map<String ,Object>> dyGettagIdList(Page<WorksInfo> page,Integer type,Integer tagId);
    List<Map<String ,Object>> dyGettagIdList(Integer type,Integer tagId);

    //worksrecommend
    @Select("SELECT *" +
            "FROM works_info a " +
            "INNER JOIN user_info b " +
            "ON a.user = b.user" +
            " WHERE a.recommend = 1" )
    List<Map<String ,Object>> dyrecommendList(Page<WorksInfo> page);

}
