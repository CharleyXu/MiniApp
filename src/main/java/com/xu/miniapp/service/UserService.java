package com.xu.miniapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.xu.miniapp.util.JacksonUtil;
import com.xu.miniapp.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private WxMaService wxMaService;

    /**
     * 登陆接口
     */
    public String login(String code) throws WxErrorException {
        if (StringUtils.isBlank(code)) {
            return "empty jsCode";
        }
        WxMaJscode2SessionResult session = wxMaService.getUserService()
            .getSessionInfo(code);
        log.info(session.getSessionKey());
        log.info(session.getOpenid());
        return JacksonUtil.obj2json(session);
    }

    /**
     * 获取用户信息接口
     */
    public String info(UserInfo userInfo) {
        // 用户信息校验
        if (!wxMaService.getUserService()
            .checkUserInfo(userInfo.getSessionKey(), userInfo.getRawData(),
                userInfo.getSignature())) {
            return "user check failed";
        }
        // 解密用户信息
        WxMaUserInfo decryptUserInfo = wxMaService.getUserService()
            .getUserInfo(userInfo.getSessionKey(), userInfo.getEncryptedData(), userInfo.getIv());

        return JacksonUtil.obj2json(decryptUserInfo);
    }

    /**
     * 获取用户绑定手机号信息
     */
    public String phone(UserInfo userInfo) {
        // 用户信息校验
        if (!wxMaService.getUserService()
            .checkUserInfo(userInfo.getSessionKey(), userInfo.getRawData(),
                userInfo.getSignature())) {
            return "user check failed";
        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService()
            .getPhoneNoInfo(userInfo.getSessionKey(), userInfo.getEncryptedData(),
                userInfo.getIv());
        return JacksonUtil.obj2json(phoneNoInfo);
    }
}
