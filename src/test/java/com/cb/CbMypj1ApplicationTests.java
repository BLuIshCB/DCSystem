package com.cb;


import com.cb.server_admin.mapper.EmployeeMapper;
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

	@Test
	void contextLoads() {

	}

}
