package com.minqiliang.controller;

import com.minqiliang.pojo.Result;
import com.minqiliang.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传,文件名{}", image);

        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问路径为：{}", url);

        return Result.success(url);
    }

}
