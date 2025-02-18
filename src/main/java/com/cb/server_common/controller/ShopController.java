package com.cb.server_common.controller;

import com.cb.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("ShopController")
@RequestMapping("/common/shop")
@Api(tags = "店铺相关接口")
@Slf4j
@CrossOrigin
public class ShopController {

    public static final String KEY = "shop:SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置店铺的营业状态
     * @return
     */
    @PutMapping("")
    @ApiOperation("更改店铺的营业状态")
    public Result setStatus(){

        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);

        if(status == 1){
            status = 0;
        }else{
            status = 1;
        }
        redisTemplate.opsForValue().set(KEY,status);
        log.info("更改店铺营业状态为：{}",status);
        return Result.success();
    }

    /**
     * 获取店铺的营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
//        log.info("获取到店铺的营业状态为：{}",status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
