package com.xu.miniapp.controller;

import com.xu.miniapp.service.AppService;
import com.xu.miniapp.vo.LoginInfo;
import com.xu.miniapp.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序用户接口
 */
@RestController
@RequestMapping("/wx/user/{appId}")
@Slf4j
public class WxMaUserController {

    @Autowired
    private AppService appService;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public String login(@PathVariable String appId, String code) throws WxErrorException {
        return appService.login(new LoginInfo(appId, code));
    }

    /**
     * 获取用户信息接口
     */
    @GetMapping("/info")
    public String info(@PathVariable String appId, String sessionKey,
        String signature, String rawData, String encryptedData, String iv) {
        return appService
            .info(new UserInfo(appId, sessionKey, signature, rawData, encryptedData, iv));
    }

    /**
     * 获取用户绑定手机号信息
     */
    @GetMapping("/phone")
    public String phone(@PathVariable String appId, String sessionKey, String signature,
        String rawData, String encryptedData, String iv) {
        return appService
            .phone(new UserInfo(appId, sessionKey, signature, rawData, encryptedData, iv));
    }

}
