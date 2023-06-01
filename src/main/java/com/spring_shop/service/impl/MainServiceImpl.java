package com.spring_shop.service.impl;

import com.spring_shop.service.MainService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class MainServiceImpl implements MainService {

    @Value("${shop.upload.image.path}")
    public String imageUploadPath;

    @Override
    public byte[] getImage(String image) throws IOException {
        File file = new File(imageUploadPath + image);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return new byte[0];
    }
}