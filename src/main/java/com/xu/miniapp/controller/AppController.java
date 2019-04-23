package com.xu.miniapp.controller;

/**
 * @author CharleyXu Created on 2019/4/22.
 */

import com.xu.miniapp.common.ResponseMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class AppController {

  @PostMapping("/login")
  @ApiOperation(value = "小程序登录", httpMethod = "POST", notes = "小程序登录")
  public ResponseMessage login(
      @ApiParam(required = true, value = "临时登录凭证code", name = "code")
      @RequestParam(name = "code")
          String code) {
    return ResponseMessage.success();
  }
}
