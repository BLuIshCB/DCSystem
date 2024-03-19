package com.cb;


import com.cb.mapper.CategoryMapper;
import com.cb.mapper.EmployeeMapper;
import com.cb.pojo.page.CategoryPageQueryDTO;
import com.cb.server_admin.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CbMypj1ApplicationTests {
	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	EmployeeService employeeService;
@Autowired
	CategoryMapper categoryMapper;
	@Test
	void contextLoads() {
		CategoryPageQueryDTO  pageInfo = new CategoryPageQueryDTO(1, 1, "", 1);
		System.out.println(categoryMapper.pageQuery(pageInfo));

//		System.out.println(categoryMapper.select());

	}

}
