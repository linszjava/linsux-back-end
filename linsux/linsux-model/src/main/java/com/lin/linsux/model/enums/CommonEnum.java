package com.lin.linsux.model.enums;

import lombok.Getter;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/30 18:34
 */
@Getter
public enum CommonEnum {

    /**
     * 通用枚举
     */
    NOT_DELETED("0"),

    DELETED("1");

    private final String value;

    CommonEnum(String value) {
        this.value = value;
    }
}
