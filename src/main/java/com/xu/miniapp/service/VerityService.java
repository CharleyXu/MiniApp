package com.xu.miniapp.service;

import com.xu.miniapp.config.WxMaProperties;
import com.xu.miniapp.util.SHA1Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@Service
public class VerityService {

    @Autowired
    private WxMaProperties wxMaProperties;

    public String verify(String signature, String timestamp, String nonce, String echostr) {
        String encrypt = SHA1Util.encrypt(wxMaProperties.getToken(), timestamp, nonce);
        if (signature.equals(encrypt)) {
            return echostr;
        }
        return null;
    }
}
