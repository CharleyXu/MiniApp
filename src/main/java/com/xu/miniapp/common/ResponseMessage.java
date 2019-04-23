package com.xu.miniapp.common;

import com.xu.miniapp.exception.ErrorCode;
import lombok.Data;

@Data
public class ResponseMessage<T> {

  //错误码
  private Integer code;
  //信息描述
  private String message;
  //具体的信息内容
  private T data;

  public static ResponseMessage success(Object object, String message) {
    ResponseMessage<Object> responseMessage = new ResponseMessage<>();
    responseMessage.setCode(ErrorCode.SUCCESS.getCode());
    responseMessage.setData(object);
    if (message != null) {
      responseMessage.setMessage(message);
    } else {
      responseMessage.setMessage(ErrorCode.SUCCESS.getMsg());
    }
    return responseMessage;
  }

  /**
   * 操作成功不返回消息
   */
  public static ResponseMessage success() {
    return success(null, null);
  }

  public static ResponseMessage success(Object obj) {
    return success(obj, null);
  }

  public static ResponseMessage success(String message) {
    return success(null, message);
  }

  public static ResponseMessage error(int code, String msg) {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setCode(code);
    responseMessage.setMessage(msg);
    return responseMessage;
  }

  /**
   * 操作失败返回消息
   */
  public static ResponseMessage error(ErrorCode resultEnum) {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setCode(resultEnum.getCode());
    responseMessage.setMessage(resultEnum.getMsg());
    return responseMessage;
  }


}
