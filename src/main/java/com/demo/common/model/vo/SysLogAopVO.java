package com.demo.common.model.vo;

import com.demo.common.mybatis.AbstractVO;
import lombok.Data;

/**
 * 值对象
 * Created by jky on 2021/04/28.
 */
@Data
public class SysLogAopVO extends AbstractVO {
    private String logUuid;
    private String logModular;
    private String logExplain;//新增 修改 删除
    private String logParam;
    private String logIp;
    private String logMac;
    private String logRemark;

    private String logResult;

    private Object result;

    public SysLogAopVO(){}

    public SysLogAopVO(String logUuid, String logModular, String logExplain,
                    String logParam, String logIp, String logMac, String logRemark,Object result) {
        this.logUuid = logUuid;
        this.logModular = logModular;
        this.logExplain = logExplain;
        this.logParam = logParam;
        this.logIp = logIp;
        this.logMac = logMac;
        this.logRemark = logRemark;
        this.result = result;
    }
}
