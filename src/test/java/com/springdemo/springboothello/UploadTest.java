package com.springdemo.springboothello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UploadTest {

    @Autowired
    private final FileServiceImp fileServiceImp;

    public UploadTest(FileServiceImp fileServiceImp) {
        this.fileServiceImp = fileServiceImp;
    }

    @Test
    public void uploadTest() {
//        File file=new File("")
//        fileServiceImp.uploadFile()
    }
}
