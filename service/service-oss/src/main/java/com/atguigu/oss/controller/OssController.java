package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

/**
 * @Author xwj
 * @CreateTime 2021/5/5 14:27
 */

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像的办法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取上传文件，multicastChannel
        //返回上传到OSS的路径
        String url = ossService.uploadOssFileAvtar(file);
        return  R.ok().data("url",url);

    }
}
