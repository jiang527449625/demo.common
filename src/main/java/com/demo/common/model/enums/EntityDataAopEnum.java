package com.demo.common.model.enums;

/**
 * @author :jky
 * @Description: aop数据补全枚举
 * @ Date: Created in 2021-04-27 09:53
 */
public enum EntityDataAopEnum {
    UUID("id", "主键"),

    CREATE_TIME("createAt", "创建时间"),
    CREATOR("createBy", "创建人"),
    UPDATE_TIME("updateAt", "修改时间"),
    UPDATOR("updateBy", "修改人"),

    WHETHER_ENABLE("whetherEnable", "是否启用(1.启用；0.禁用)"),
    DEL_FLAG("delFlag", "删除标识(1.正常；0.删除)"),
    ORG_UUID("orgUuid", "机构uuid"),

    TYPE_INSERT("insert", "添加类型"),
    TYPE_UPDATE("update", "修改类型"),
    TYPE_SELECT("select", "查询类型");

    private String code;

    private String message;

    EntityDataAopEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EntityDataAopEnum type(String code) {
        EntityDataAopEnum result = null;
        EntityDataAopEnum[] values = EntityDataAopEnum.values();
        for (EntityDataAopEnum value : values) {
            if (value.getCode().equals(code)) {
                result = value;
                break;
            }
        }
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
