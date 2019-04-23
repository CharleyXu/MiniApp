package com.xu.miniapp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@Data
@AllArgsConstructor
public class UserInfo {

    private String appId;
    private String sessionKey;
    private String signature;
    private String rawData;
    private String encryptedData;
    private String iv;
}
