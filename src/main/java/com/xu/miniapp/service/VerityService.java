package com.xu.miniapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import com.xu.miniapp.config.WxMaProperties;
import com.xu.miniapp.util.SHA1Util;
import com.xu.miniapp.vo.RequestInfo;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@Service
@Slf4j
public class VerityService {

    @Autowired
    private WxMaProperties wxMaProperties;
    @Autowired
    private WxMaMessageRouter wxMaMessageRouter;
    @Autowired
    private WxMaService wxMaService;

    public String verify(String signature, String timestamp, String nonce, String echostr) {
        String encrypt = SHA1Util.encrypt(wxMaProperties.getToken(), timestamp, nonce);
        if (signature.equals(encrypt)) {
            return echostr;
        }
        //第三方API调用方式
//        if (wxService.checkSignature(timestamp, nonce, signature)) {
//            return echostr;
//        }
        return "非法请求";
    }

    public String post(RequestInfo requestInfo) {

        boolean isJson = Objects.equals(wxMaService.getWxMaConfig().getMsgDataFormat(),
            WxMaConstants.MsgDataFormat.JSON);
        if (StringUtils.isBlank(requestInfo.getEncryptType())) {
            // 明文传输的消息
            WxMaMessage inMessage;
            if (isJson) {
                inMessage = WxMaMessage.fromJson(requestInfo.getRequestBody());
            } else {
                inMessage = WxMaMessage.fromXml(requestInfo.getRequestBody());
            }
            this.route(inMessage);
            return "success";
        }

        if ("aes".equals(requestInfo.getEncryptType())) {
            // 是aes加密的消息
            WxMaMessage inMessage;
            if (isJson) {
                inMessage = WxMaMessage
                    .fromEncryptedJson(requestInfo.getRequestBody(), wxMaService.getWxMaConfig());
            } else {
                inMessage = WxMaMessage
                    .fromEncryptedXml(requestInfo.getRequestBody(), wxMaService.getWxMaConfig(),
                        requestInfo.getTimestamp(), requestInfo.getNonce(),
                        requestInfo.getMsgSignature());
            }
            this.route(inMessage);
            return "success";
        }
        throw new RuntimeException("不可识别的加密类型：" + requestInfo.getEncryptType());
    }

    private void route(WxMaMessage message) {
        try {
            wxMaMessageRouter.route(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
