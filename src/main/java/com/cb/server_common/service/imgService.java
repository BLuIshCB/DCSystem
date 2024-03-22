package com.cb.server_common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.common.exception.ImageErrorException;
import com.cb.mapper.imageMapper;
import com.cb.pojo.entity.img;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class imgService {
    @Autowired
    imageMapper imgMapper;

    public byte[] getImg(int dishid ,int imageid) throws IOException {
            QueryWrapper<img> wrapper = new QueryWrapper<>();
            wrapper.select("img_url").eq("dish_id", dishid).eq("img_index",imageid);
            System.out.println(imgMapper.selectOne(wrapper));
            //获得url地址
            String fileName =  imgMapper.selectOne(wrapper).getImgUrl();

            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
    }

    public boolean set(img img){
        return  imgMapper.insert(img)>0;
    }
}
