package com.cb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.common.annotation.AutoFill;
import com.cb.common.enumeration.OperationType;
import com.cb.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper  {


    @Select("select * from user where name = #{name}")
    User getByName(String name);

//    @Insert("insert into user values")
//    int registerUser()

    /**
     * 插入数据
     * @param user
     */

    void insert(User user);

    @Select("select * from user where id = #{id}")
    User getById(Long userId);

    @Select("select * from user where phone = #{phone}")
    User getByPhone(String phone);

    /**
     * 根据动态条件统计用户数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
