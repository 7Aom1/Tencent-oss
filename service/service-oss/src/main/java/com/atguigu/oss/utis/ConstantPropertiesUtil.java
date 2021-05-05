package com.atguigu.oss.utis;

/**
 * @Author xwj
 * @CreateTime 2021/5/5 14:17
 */

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 常量类，读取配置文件application.properties中的配置
 */
@Component
@PropertySource("classpath:application.properties")
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${tencent.oss.file.endpoint}")
    private String endpoint;

    @Value("${tencent.oss.file.keyid}")
    private String keyId;

    @Value("${tencent.oss.file.keysecret}")
    private String keySecret;


    @Value("${tencent.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST ;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
