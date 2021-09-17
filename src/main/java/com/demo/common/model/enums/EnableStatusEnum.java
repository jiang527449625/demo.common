package com.demo.common.model.enums;

/**
 * @author :jky
 * @Description: 删除状态
 * @ Date: Created in 2021-08-10 15:33
 */
public enum EnableStatusEnum {
    YES_ENUM("0", "启用"),
    NO_ENUM("1", "禁用");

    private String code;

    private String message;

    EnableStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EnableStatusEnum type(String code) {
        EnableStatusEnum result = null;
        EnableStatusEnum[] values = EnableStatusEnum.values();
        for (EnableStatusEnum value : values) {
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
