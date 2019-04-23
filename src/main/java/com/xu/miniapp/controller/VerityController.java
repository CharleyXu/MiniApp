package com.xu.miniapp.controller;

import com.xu.miniapp.service.VerityService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@RestController
@RequestMapping("/api/v1/")
public class VerityController {

    @Autowired
    private VerityService verityService;

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
}
