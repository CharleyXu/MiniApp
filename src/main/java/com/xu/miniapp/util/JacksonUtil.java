package com.xu.miniapp.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

/**
 * @author CharleyXu Created on 2019/1/3.
 */
public class JacksonUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  static {
    // 全部字段序列化
    objectMapper.setSerializationInclusion(Include.ALWAYS);
    //取消默认转换timestamps形式
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    //忽略空Bean转json的错误
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    //忽略在json字符串中存在而在java对象中不存在对应属性的情况
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }


  public static String obj2json(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T json2obj(String json, Class<T> c) {
    try {
      return objectMapper.readValue(json, c);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
