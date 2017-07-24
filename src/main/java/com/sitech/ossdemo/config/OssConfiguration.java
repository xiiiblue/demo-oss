package com.sitech.ossdemo.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfiguration {
    @Value("${aliyun.oss.access-key-id}")
    String accessKeyId;
    @Value("${aliyun.oss.access-key-secret}")
    String accessKeySecret;
    @Value("${aliyun.oss.endpoint}")
    String endpoint;

    @Bean
    OSSClient ossClient() {
        return new OSSClient("https://" + this.endpoint, this.accessKeyId, this.accessKeySecret);
    }

}