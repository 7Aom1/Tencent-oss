package com.atguigu.oss.service.impl;

import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utis.ConstantPropertiesUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Author xwj
 * @CreateTime 2021/5/5 14:28
 */

@Service
public class OssServiceImpl implements OssService {

    @Value("${tencent.oss.file.bucketname}")
    private String bucketName;

    @Override
    public String uploadOssFileAvtar(MultipartFile Uploadfile) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(ConstantPropertiesUtil.END_POINT);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);


        try {
            //获得文件名称
            InputStream inputStream = Uploadfile.getInputStream();
            // 指定文件将要存放的存储桶
            String bucketName = this.bucketName;
            System.out.println("------------------------"+Uploadfile.getOriginalFilename()+"-----------------------");
            // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String key = "guliSchool/"+Uploadfile.getName();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream,null);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

            //将上传后的url地址拼接出来
            String uploadUrl = "http://" + bucketName + "." + ConstantPropertiesUtil.END_POINT + "/" + key;
            return uploadUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
