package com.sitech.ossdemo.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bluexiii on 24/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OssComponentTest {
    private Logger logger = LoggerFactory.getLogger(OssComponentTest.class);
    private String localPath;
    @Autowired
    OssComponent ossComponent;

    public OssComponentTest() {
        localPath = System.getProperty("user.dir") + "/" + "upload/";
    }


    @Test
    public void urlFromFileKey() throws Exception {
        String bucket = "foobar";
        String fileKey = "0c2eb1357a454c71b71aa20462951ac4.jpg";
        String url = ossComponent.urlFromFileKey(bucket, fileKey);

        logger.debug(url);
        assertNotNull(url);
    }

    @Test
    public void putObject() throws Exception {
        String bucket = "foobar";
        String filePath = localPath + "sonic.jpg";

        String fileKey = ossComponent.putObject(bucket, filePath, null);
        logger.debug(fileKey);
        assertNotNull(fileKey);

        fileKey = ossComponent.putObject(bucket, filePath, "sonic.jpg");
        logger.debug(fileKey);
        assertNotNull(fileKey);
    }

    @Test
    public void getObject() throws Exception {
        String bucket = "foobar";
        String fileKey = "0c2eb1357a454c71b71aa20462951ac4.jpg";
        String localPath = this.localPath;

        String localFileKey = ossComponent.getObject(bucket, fileKey, localPath);

        logger.debug(localFileKey);
        assertNotNull(localFileKey);
    }

    @Test
    public void checkObject() throws Exception {
        String bucket = "foobar";
        String fileKey = "0c2eb1357a454c71b71aa20462951ac4.jpg";

        boolean flag = ossComponent.checkObject(bucket, fileKey);

        assertTrue(flag);
    }

    @Test
    public void listObjects() throws Exception {
        String bucket = "foobar";
        String keyPrifix = "";

        List<String> list = ossComponent.listObjects(bucket, keyPrifix);

        logger.debug(list.toString());
        assertTrue(list.size() > 0);
    }

    @Test
    public void deleteObject() throws Exception {
        String bucket = "foobar";
        String fileKey = "sonic.jpg";

        ossComponent.deleteObject(bucket, fileKey);
    }
}