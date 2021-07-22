package com.pigposter.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.pigposter.pp.repo.*;
import com.fasterxml.jackson.annotation.*;   
import org.springframework.web.multipart.MultipartFile;                    
@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public String httpUpload(@RequestParam("file")MultipartFile file)
    {
        
        String fileName = file.getOriginalFilename();  // 文件名
        String path = "E:\\pigposter\\pp\\src\\main\\resources\\static\\img"+'/'+ fileName;
        File dest = new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            return "bad";
        }
        return path;
    }
}
