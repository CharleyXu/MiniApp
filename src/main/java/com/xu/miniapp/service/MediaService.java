package com.xu.miniapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author CharleyXu Created on 2019/4/24.
 */
@Service
@Slf4j
public class MediaService {

    @Autowired
    private WxMaService wxMaService;

    public List<String> uploadMedia(HttpServletRequest request) {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(
            request.getSession().getServletContext());
        if (!resolver.isMultipart(request)) {
            return Lists.newArrayList();
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multiRequest.getFileNames();
        List<String> result = Lists.newArrayList();
        while (it.hasNext()) {
            try {
                MultipartFile file = multiRequest.getFile(it.next());
                File newFile = new File(Files.createTempDir(), file.getOriginalFilename());
                log.info("filePath is ：" + newFile.toString());
                file.transferTo(newFile);
                WxMediaUploadResult uploadResult = wxMaService.getMediaService()
                    .uploadMedia(WxMaConstants.KefuMsgType.IMAGE, newFile);
                log.info("media_id ： " + uploadResult.getMediaId());
                result.add(uploadResult.getMediaId());
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (WxErrorException e) {
                log.error(e.getMessage(), e);
            }
        }

        return result;
    }

    public File getMedia(String mediaId) throws WxErrorException {
        return wxMaService.getMediaService().getMedia(mediaId);
    }
}
