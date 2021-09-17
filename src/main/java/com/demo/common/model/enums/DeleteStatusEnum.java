package com.demo.common.model.enums;

/**
 * @author :jky
 * @Description: 删除状态
 * @ Date: Created in 2021-08-10 15:33
 */
public enum DeleteStatusEnum {
    NO_ENUM("0", "未删除"),
    YES_ENUM("1", "删除");

    private String code;

    private String message;

    DeleteStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static DeleteStatusEnum type(String code) {
        DeleteStatusEnum result = null;
        DeleteStatusEnum[] values = DeleteStatusEnum.values();
        for (DeleteStatusEnum value : values) {
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
