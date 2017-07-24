package com.sitech.ossdemo.component;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by bluexiii on 24/07/2017.
 */
@Component
public class OssComponent {
    private Logger logger = LoggerFactory.getLogger(OssComponent.class);
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Autowired
    private OSSClient ossClient;


    /**
     * 通过bucket与fileKey获取URL
     *
     * @param bucket
     * @param fileKey
     * @return
     */
    public String urlFromFileKey(String bucket, String fileKey) {
        return "http://" + bucket + "." + this.endpoint + "/" + fileKey;
    }

    /**
     * 上传本地文件
     *
     * @param bucket   Bucket
     * @param filePath 本地文件路径
     * @param fileKey  指定OSS文件名，传空时自动生成UUID
     * @return
     * @throws FileNotFoundException
     */
    public String putObject(String bucket, String filePath, String fileKey) throws FileNotFoundException {
        if (fileKey == null) {
            fileKey = UUID.randomUUID().toString().replaceAll("-", "") + filePath.substring(filePath.lastIndexOf("."));
        }

        ossClient.putObject(bucket, fileKey, new File(filePath));

        logger.info("OSS putObject success! fileKey={}", fileKey);
        return fileKey;
    }

    /**
     * 下载文件到本地目录
     *
     * @param bucket    Bucket
     * @param fileKey   OSS文件名
     * @param localPath 本地文件目录
     * @return
     */
    public String getObject(String bucket, String fileKey, String localPath) {
        String localFileKey = localPath + fileKey;
        ossClient.getObject(new GetObjectRequest(bucket, fileKey), new File(localFileKey));

        logger.info("OSS getObject success! localFileKey={}", localFileKey);
        return localFileKey;
    }


    /**
     * 检查文件是否存在
     *
     * @param bucket  Bucket
     * @param fileKey OSS文件名
     * @return
     */
    public boolean checkObject(String bucket, String fileKey) {
        return ossClient.doesObjectExist(bucket, fileKey);
    }


    /**
     * 列出所有文件
     *
     * @param bucket    Bucket
     * @param keyPrifix 文件名前缀
     */
    public List<String> listObjects(String bucket, String keyPrifix) {
        List<String> fileList = new ArrayList<>();

        ObjectListing objectListing = ossClient.listObjects(bucket, keyPrifix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            fileList.add(bucket);
        }

        return fileList;
    }

    /**
     * 删除文件
     *
     * @param bucket
     * @param fileKey
     */
    public void deleteObject(String bucket, String fileKey) {
        ossClient.deleteObject(bucket, fileKey);
    }

}
