package com.cb.mapper;


import com.cb.common.annotation.AutoFill;
import com.cb.common.enumeration.OperationType;
import com.cb.pojo.entity.Category;
import com.cb.pojo.page.CategoryPageQueryDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 插入数据
     *
     * @param category
     */
    @Insert("insert into category( name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " ( #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Category category);

    /**
     *  查询全部分类
     */
    @Select("select * from category")
    List<Category> select();


    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);
//    拿名字
    @Select("select name from category where id =#{id}")
    String getName(Long id);
    /**
     * 根据id修改分类
     *
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    //查询状态
    @Select("select status from category where id = #{id}")
    int selectStatus (Long id );


}
