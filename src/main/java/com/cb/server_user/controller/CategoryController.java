package com.cb.server_user.controller;


import com.cb.common.result.Result;
import com.cb.pojo.entity.Category;
import com.cb.pojo.vo.CategoryVO;
import com.cb.pojo.vo.DishVO;
import com.cb.server_admin.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/query/category")
@Api(tags = "C端-分类接口")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询分类
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询分类")
    public Result<List<CategoryVO>> list() {
        //构造redis中的key，规则：dish_分类id
        String key = "category" ;

        //查询redis中是否存在菜品数据
        List<CategoryVO> Redislist = (List<CategoryVO>)redisTemplate.opsForValue().get(key);

        if(Redislist != null && Redislist.size() > 0){
            //如果存在，直接返回，无须查询数据库
            return Result.success(Redislist);
        }



        List<Category> list = categoryService.list();
        List<CategoryVO> voList = new ArrayList<>();

        //如果不存在，查询数据库，将查询到的数据放入redis中
        for (Category category : list) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category,categoryVO);
            voList.add(categoryVO);
        }
        redisTemplate.opsForValue().set(key, voList);
        return Result.success(voList);
    }
}
