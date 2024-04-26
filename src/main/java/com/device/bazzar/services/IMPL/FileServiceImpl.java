package com.device.bazzar.services.IMPL;

import org.slf4j.Logger;
import com.device.bazzar.exception.BadApiRequest;
import com.device.bazzar.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger;

    @Override
    public String uploadfile(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String FileName = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String FileNameWithExtension = FileName+extension;
        String FullPathWithFileName = path + File.separator + FileNameWithExtension;
        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
            File folder = new File(path);
            if(!folder.exists()){
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(FullPathWithFileName));
        }else {
            throw new BadApiRequest("File with" + extension + " not allowed");
        }

        return FileNameWithExtension;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        //logger.info("path --> ", fullPath);
        InputStream inputStream=  new FileInputStream(fullPath);
        return inputStream;
    }
}
