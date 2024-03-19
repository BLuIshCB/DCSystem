package com.cb;


import com.cb.mapper.CategoryMapper;
import com.cb.mapper.DishMapper;
import com.cb.mapper.EmployeeMapper;
import com.cb.pojo.entity.Category;
import com.cb.pojo.entity.Dish;
import com.cb.pojo.page.CategoryPageQueryDTO;
import com.cb.server_admin.service.EmployeeService;
import com.github.houbb.data.factory.core.util.DataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	void contextLoads() {
//		System.out.println(dishMapper.countByCategoryId(1l));
		Dish dish = DataUtil.build(Dish.class);
		System.out.println(dish);
//		dishMapper.insert(dish);
//		dishMapper.getById(2l);
	}

}
