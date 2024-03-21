package com.cb;


import com.cb.mapper.CategoryMapper;
import com.cb.mapper.DishMapper;
import com.cb.mapper.EmployeeMapper;
import com.cb.pojo.dto.DishDTO;
import com.cb.pojo.entity.Category;
import com.cb.pojo.entity.Dish;
import com.cb.pojo.page.CategoryPageQueryDTO;
import com.cb.pojo.page.DishPageQueryDTO;
import com.cb.server_admin.service.DishService;
import com.cb.server_admin.service.EmployeeService;
import com.github.houbb.data.factory.core.util.DataUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
class CbMypj1ApplicationTests {
	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	EmployeeService employeeService;
@Autowired
	CategoryMapper categoryMapper;
@Autowired
	DishMapper dishMapper;
	@Test
	void utilTest() {
//		Dish dish= DataUtil.build(Dish.class);//生成随机对象

	}
	@Autowired
	DishService dishService;
	@Autowired
	RedisTemplate redisTemplate;
	@Test
	void Test1() {

//		dishService.startOrStop(0,2l);
		DishPageQueryDTO d = new DishPageQueryDTO();
		d.setPage(1);
		d.setPageSize(2);
//		d.setName("test");
		String a = "2a";
		String b= "2b";
		System.out.println(dishService.pageQuery(d));
		redisTemplate.opsForValue().set(a,b);
	}
}
