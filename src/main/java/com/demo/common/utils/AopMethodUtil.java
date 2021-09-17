package com.demo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.demo.common.config.LogAnno;
import com.demo.common.model.vo.SysLogAopVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :jky
 * @ Date: Created in 2021-04-26 11:34
 */
public class AopMethodUtil {

    /**
     * 拦截器公共方法(日志生成)
     * @param pjp
     * @return
     * @throws Throwable
     */
    public static SysLogAopVO loginsert(ProceedingJoinPoint pjp, LogAnno logAnno) throws Throwable {
        // 获取参数
        String param = JSONArray.toJSONString(pjp.getArgs());
        Object result = null;
//        获取IP
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = IpUtils.getClientAddress(request);
//        mac地址获取（localhost会影响速度）
        String macAddress = GetMacAddressUtils.getMacAddrByIp(ipAddress);
        //让代理方法执行
        result = pjp.proceed();
        // 2.相当于后置通知(方法成功执行之后走这里)
        // 创建一个日志对象(准备记录日志)
        SysLogAopVO logTableVO = new SysLogAopVO(SnowflakeIdUtil.newStringId(),logAnno.logModular(),
                logAnno.logExplain(),param,ipAddress,macAddress,logAnno.logRemark(),result);
        return logTableVO;
    }
}
