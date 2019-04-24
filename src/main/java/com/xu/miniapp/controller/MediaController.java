package com.xu.miniapp.controller;

import com.xu.miniapp.service.MediaService;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 临时素材接口
 */
@RestController
@RequestMapping("/api/v1/wx/media")
@Slf4j
public class MediaController {

    @Autowired
    private MediaService mediaService;

    /**
     * 上传临时素材
     *
     * @return 素材的media_id列表，实际上如果有的话，只会有一个
     */
    @PostMapping("/upload")
    public List<String> uploadMedia(HttpServletRequest request) {
        return mediaService.uploadMedia(request);
    }

    /**
     * 下载临时素材
     */
    @GetMapping("/download/{mediaId}")
    public File getMedia(@PathVariable String mediaId)
        throws WxErrorException {
        return mediaService.getMedia(mediaId);
    }
}
