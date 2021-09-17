package com.demo.common.mybatis;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public abstract class AbstractVO {

    /** 当前页 默认 0 */
    @ApiModelProperty(name = "pageNum",value = "当前页")
    protected Integer pageNum;
    /** 当前页 默认 0 */
    @ApiModelProperty(hidden = true)
    protected Integer pageNumber;
    /** 页大小 默认 10 */
    @ApiModelProperty(name = "pageSize",value = "页大小")
    protected Integer pageSize;
    /** 排序字段 */
    @ApiModelProperty(name = "sortField",value = "排序字段")
    protected String sortField;
    /** 排序方式 ASC 、DESC */
    @ApiModelProperty(name = "order",value = "排序方式 ASC 、DESC")
    protected String order;
    @ApiModelProperty(name = "startTime",value = "开始时间")
    protected Date startTime; // 开始时间
    @ApiModelProperty(name = "endTime",value = "结束时间")
    protected Date endTime; // 结束时间
    //    protected String userUuid;
//    protected String userName;
//    protected String userRole;
//    protected String userPassword;
    @ApiModelProperty(hidden = true)
    protected String token;
    @ApiModelProperty(hidden = true)
    protected String cityCode;//城市编码
    @ApiModelProperty(hidden = true)
    protected String orgIdSQL;//权限拼接语句   ——jky

    @ApiModelProperty(name = "whetherEnable",value = "是否启用(0.启用；1.禁用)")
    protected String whetherEnable;
    @ApiModelProperty(name = "delFlag",value = "删除标识(0.正常；1.删除)")
    protected String delFlag;
    @ApiModelProperty(hidden = true)
    protected String createBy;
    @ApiModelProperty(hidden = true)
    protected Date createAt;
    @ApiModelProperty(hidden = true)
    protected String updateBy;
    @ApiModelProperty(hidden = true)
    protected Date updateAt;
}
