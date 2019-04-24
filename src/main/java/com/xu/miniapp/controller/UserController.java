package com.xu.miniapp.controller;

import com.xu.miniapp.service.UserService;
import com.xu.miniapp.vo.UserInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序用户接口
 *
 * @author CharleyXu Created on 2019/4/22.
 */
@RestController
@RequestMapping("/api/v1/wx/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    @ApiOperation(value = "小程序登录", httpMethod = "POST", notes = "小程序登录")
    public String login(
        @ApiParam(required = true, value = "临时登录凭证code", name = "code")
        @RequestParam(name = "code")
            String code) throws WxErrorException {
        return userService.login(code);
    }

    /**
     * 获取用户信息接口
     */
    @GetMapping("/info")
    public String info(String sessionKey,
        String signature, String rawData, String encryptedData, String iv) {
        return userService
            .info(new UserInfo(sessionKey, signature, rawData, encryptedData, iv));
    }

    /**
     * 获取用户绑定手机号信息
     */
    @GetMapping("/phone")
    public String phone(String sessionKey, String signature,
        String rawData, String encryptedData, String iv) {
        return userService
            .phone(new UserInfo(sessionKey, signature, rawData, encryptedData, iv));
    }
}
