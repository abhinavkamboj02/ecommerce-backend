package com.device.bazzar.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    public String uploadfile(MultipartFile file, String path) throws IOException;
    InputStream getResource(String path, String name) throws FileNotFoundException;
}
