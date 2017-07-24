# demo-oss

## 简介
demo-oss是一个阿里云OSS对象存储的示例程序

## OSS SDK下载
https://promotion.aliyun.com/ntms/act/ossdoclist.html

## OSS SDK源码
https://github.com/aliyun/aliyun-oss-java-sdk

## OSS Maven依赖
```XML
<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>2.8.1</version>
</dependency>
```

## OSS官方文档
https://help.aliyun.com/document_detail/32009.html


## 申请及配置OSS流程
1. 登录aliyun后，进入OSS控制台:
[https://oss.console.aliyun.com/overview](https://oss.console.aliyun.com/overview)

2. 右侧点击"购买资源包"，选择合适的档位，付费购买。

3. 右侧点击"新建Bucket"，输入一个全局唯一的bucket名，并选择所属地域，新建Bucket。

4. 左侧列表中，进入新建的Bucket，可以查询到"Endpoint"

5. 获取AK，可以直接访问链接：
[https://ak-console.aliyun.com/#/accesskey](https://ak-console.aliyun.com/#/accesskey)