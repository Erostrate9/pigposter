package com.pigposter.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.pigposter.pp.repo.*;
import com.fasterxml.jackson.annotation.*;   
import org.springframework.web.multipart.MultipartFile;

                  
@RestController
@RequestMapping("/file")

public class FileController {
    
    @PostMapping("/upload")
    public List<String> httpUpload(@RequestParam("file")MultipartFile files[])
    {
        List<String> paths = new ArrayList<>();
        for(int i = 0;i < files.length;i++)
        {

            String fileName = files[i].getOriginalFilename();  // 文件名
            String path = "\\img\\"+ fileName;
            File dest = new File("E:\\pigposter\\pp\\src\\main\\resources\\static"+path);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                paths.add("error");
            }
            paths.add(path);
        }
        return paths;
    }
}
