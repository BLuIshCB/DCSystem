package com.cb.server_admin.service;

import com.cb.common.constant.MessageConstant;
import com.cb.common.constant.StatusConstant;
import com.cb.common.exception.AccountLockedException;
import com.cb.common.exception.AccountNotFoundException;
import com.cb.common.exception.PasswordErrorException;
import com.cb.pojo.dto.EmployeeLoginDTO;
import com.cb.pojo.entity.Employee;
import com.cb.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */

    @Autowired
    private EmployeeMapper employeeMapper;
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端传过来的明文密码进行md5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
           
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }
}
