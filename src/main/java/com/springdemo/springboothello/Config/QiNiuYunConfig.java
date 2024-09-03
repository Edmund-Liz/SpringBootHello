package com.springdemo.springboothello.Config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class QiNiuYunConfig {

    @Value("${qiniu.path}")
    private String url;

    @Value("${qiniu.accessKey}")
    private String AccessKey;

    @Value("${qiniu.secretKey}")
    private String SecretKey;

    @Value("${qiniu.bucket}")
    private String BucketName;

}
