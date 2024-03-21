package com.cb.server_admin.controller;

import com.cb.common.constant.MessageConstant;
import com.cb.common.exception.AccountNotFoundException;
import com.cb.common.exception.ImageErrorException;
import com.cb.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class upLoadImageController {
    @PostMapping("/upLoadImage")
    public Result<String> upLoadImage( @RequestParam("dishid") String dishid,@RequestParam("imageid") String imageid,MultipartFile file) {
        try {
//            System.out.println(dishid);
//            System.out.println(imageid);
            file.transferTo(new File("D:\\CBBC\\IDEA_SPACE\\CB_MYPJ1\\myimage\\"+"dish_"+dishid+"image_"+imageid+".jpg"));
        } catch (IOException e) {
            throw new ImageErrorException(MessageConstant.IMAGE_ERROR);
        }
        return Result.success("上传成功");
    }
}
