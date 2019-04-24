package com.xu.miniapp.controller;

import com.xu.miniapp.service.VerityService;
import com.xu.miniapp.vo.RequestInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@RestController
@RequestMapping("/api/v1")
public class VerityController {

    @Autowired
    private VerityService verityService;

    /**
     * auth 接受来着微信服务器的认证信息
     */
    @GetMapping(value = "/verify")
    public String verify(
        @ApiParam(value = "加密签名", name = "signature")
        @RequestParam String signature,
        @ApiParam(value = "时间戳", name = "timestamp")
        @RequestParam String timestamp,
        @ApiParam(value = "随机数", name = "nonce")
        @RequestParam String nonce,
        @ApiParam(value = "随机字符串", name = "echostr")
        @RequestParam String echostr) {
        return verityService.verify(signature, timestamp, nonce, echostr);
    }

    @PostMapping(value = "/verify", produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
        @RequestParam(name = "msg_signature", required = false) String msgSignature,
        @RequestParam(name = "encrypt_type", required = false) String encryptType,
        @RequestParam(name = "signature", required = false) String signature,
        @RequestParam("timestamp") String timestamp,
        @RequestParam("nonce") String nonce) {
        return verityService.post(
            new RequestInfo(requestBody, msgSignature, encryptType, signature, timestamp, nonce));
    }
}
