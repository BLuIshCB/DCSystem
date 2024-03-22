package com.cb.server_admin.controller;

import com.cb.common.constant.MessageConstant;
import com.cb.common.exception.ImageErrorException;
import com.cb.common.result.Result;
import com.cb.mapper.imageMapper;
import com.cb.pojo.entity.img;
import com.cb.server_common.service.imgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
@Slf4j
public class upLoadImageController {
    @Value("${cbinfo.img.url}")
    String URL;
    @Autowired
    imgService imgService;
    @PostMapping("/upLoadImage")
    public Result<String> upLoadImage(@RequestParam("dishid") int dishid, @RequestParam("imageid") int imageid, MultipartFile file) {
        try {
            log.info("url:{}",URL);
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filePath = URL + "dish_" + dishid + "image_" + imageid +extension;
            file.transferTo(new File(filePath));
            //将文件信息保存到数据库
            img img =new img().builder().imgUrl(filePath).dishId(dishid).imgIndex(imageid).build();
            boolean flag = imgService.set(img);
            return Result.success("上传成功");
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
            return Result.error("上传失败");
    }
    @GetMapping(value = "/getImage/{dishId}/{imageid}" ,produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable  Integer dishId,@PathVariable Integer imageid ) throws Exception {
        log.info("{}/{}",dishId,imageid);
        return  imgService.getImg(dishId,imageid);
    }
}
