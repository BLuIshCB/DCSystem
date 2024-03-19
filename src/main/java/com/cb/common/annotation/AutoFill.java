package com.cb.common.annotation;



import com.cb.common.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
 * 例如创建时间、修改时间等等
 */
@Target(ElementType.METHOD)//标注注解加在哪里、这里只能加在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    //数据库操作类型：UPDATE INSERT
    OperationType value();
}
