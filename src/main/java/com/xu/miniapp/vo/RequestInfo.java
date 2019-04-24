package com.xu.miniapp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CharleyXu Created on 2019/4/24.
 */
@Data
@AllArgsConstructor
public class RequestInfo {

    private String requestBody;
    private String msgSignature;
    private String encryptType;
    private String signature;
    private String timestamp;
    private String nonce;
}
