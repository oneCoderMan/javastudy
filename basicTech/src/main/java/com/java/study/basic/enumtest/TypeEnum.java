package com.java.study.basic.enumtest;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author： yijun
 * @DATE: 2023/11/13 21:42
 * @Description 枚举定义类
 */
public enum TypeEnum {
    /**
     * pdf
     */
    PDF(0, "pdf"),
    /**
     * 视频类型
     */
    VIDEO(1, "视频"),
    /**
     * 音频类型
     */
    AUDIO(2, "音频"),
    /**
     * 文本类型
     */
    TEXT(3, "文本"),
    /**
     * 图像类型
     */
    IMAGE(4, "图像");

    /**
     * 定义的值
     */
    int code;

    /**
     * 定义的名字
     */
    String name;

    TypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getValue() {
        return code;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    /**
     * 根据数值生成枚举值
     * @param code
     * @return
     */
    public static TypeEnum getByValue(int code) {
        for(TypeEnum typeEnum : TypeEnum.values()) {
            if(typeEnum.code == code) {
                return typeEnum;
            }
        }
        throw new IllegalArgumentException("No element matches " + code);
    }
}
