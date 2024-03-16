package com.cb;

import com.cb.pojo.dto.EmployeeLoginDTO;
import com.cb.system.mapper.EmployeeMapper;
import com.cb.system.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CbMypj1ApplicationTests {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	EmployeeService employeeService;

	@Test
	void contextLoads() {
		System.out.println(employeeService.login(new EmployeeLoginDTO("admin","123456")));
	}

}
