package com.demo.common.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 * @author jky
 */
@Target(ElementType.METHOD)//方法注解
@Retention(RetentionPolicy.RUNTIME)// 运行时可见
public @interface LogAnno {
    String logTableName();// 日志记录表名
    String logModular();// 日志模块
    String logExplain();// 记录日志的操作类型
    String logRemark();// 备注
}
