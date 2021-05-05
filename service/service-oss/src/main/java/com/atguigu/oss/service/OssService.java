package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

/**
 * @Author xwj
 * @CreateTime 2021/5/5 14:27
 */
public interface OssService {
    String uploadOssFileAvtar(MultipartFile file);
}
