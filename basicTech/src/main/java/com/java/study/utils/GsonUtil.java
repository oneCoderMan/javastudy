package com.java.study.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author： yijun
 * @DATE: 2023/9/16 17:35
 * @Description
 * <dependency>
 * 			<groupId>com.google.code.gson</groupId>
 * 			<artifactId>gson</artifactId>
 * 			<version>2.8.9</version>
 * 		</dependency>
 */
public class GsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    /**
     * json字符串转换为泛型对象
     * @param jsonContent
     * @return
     * List<Person> people2 =
     * GsonUtil.fromJson(s1, new TypeToken<List<Person>>() {
     *        });
     */
    public static <T> T fromJson(String jsonContent, TypeToken<T> token) {
        return GSON.fromJson(jsonContent, token.getType());
    }

    /**
     * json 字符串转bean对象
     * @param jsonContent
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T fromJson(String jsonContent, Class<T> clazz) {
        try {
            return GSON.fromJson(jsonContent, clazz);
        } catch (JsonSyntaxException e) {
            LOGGER.error("Fail to convert json[{}] to bean[{}]", jsonContent, clazz, e);
            throw new IllegalStateException("Fail to parse json str");
        }
    }


    /**
     * Java对象转Json
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return GSON.toJson(obj);
        } catch (JsonSyntaxException e) {
            LOGGER.error("Failed to parse object to json str", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 对象转换为json字符串。
     * @param obj
     * @return 对象的json字符串，对象为空或转换失败时返回null
     */
    public static String toJsonStr(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return GSON.toJson(obj);
        } catch (JsonSyntaxException e) {
            LOGGER.error("Failed to convert object to json str", e);
            return null;
        }
    }

    /**
     * Json转换为Map
     * @param content
     * @return
     */
    public static Map<String, String> readStringMap(String content) {
        return GSON.fromJson(content, new TypeToken<Map<String, String>>() {}.getType());
    }

    public static Map<Object, Object> readObjectMap(String content) {
        return GSON.fromJson(content, Map.class);
    }
}
