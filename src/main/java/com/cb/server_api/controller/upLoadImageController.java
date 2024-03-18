package com.cb.server_api.controller;

import com.cb.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class upLoadImageController {
    @PostMapping("/upLoadImage")
    public Result<String> upLoadImage(MultipartFile file){
        try {
            file.transferTo(new File("D:\\CBBC\\IDEA_SPACE\\CB_MYPJ1\\myimage\\"+"001.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success("上传成功");
    }
}
