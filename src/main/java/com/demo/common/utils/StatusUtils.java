package com.demo.common.utils;

/**
 * 状态管理类
 * @author jky
 *
 */
public enum StatusUtils {
	ENABLE(1), DISABLE(2);


    private int code;

    StatusUtils(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
