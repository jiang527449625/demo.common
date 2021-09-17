package com.demo.common.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface ModelMethodChannelService {
    //执行方法
    void executionMethod(Field field, Method method, Object model) throws InvocationTargetException, IllegalAccessException;
}
