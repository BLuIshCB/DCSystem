package com.cb.server_user.controller;


import com.cb.common.result.PageResult;
import com.cb.common.result.Result;
import com.cb.pojo.dto.OrdersSubmitDTO;
import com.cb.pojo.vo.OrderSubmitVO;
import com.cb.pojo.vo.OrderVO;
import com.cb.server_user.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "用户端订单相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单，参数为：{}",ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }




    /**
     *  模拟订单支付
     *
     * @return
     */
    //id为订单号
    @PutMapping("/pay/{id}")
    @ApiOperation("订单支付")
    public Result payment(@PathVariable long id) throws Exception {
        log.info("----模拟订单支付,订单id为：{}-----", id);
        orderService.pay(id);
        return Result.success("支付成功");
    }


    /**
     * 历史订单查询
     *
     * @param page
     * @param pageSize
     * @param status   订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation("历史订单查询")
    public Result<PageResult> page(int page, int pageSize, Integer status) {
        PageResult pageResult = orderService.pageQuery4User(page, pageSize, status);
        return Result.success(pageResult);
    }

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /**
     * 用户取消订单
     *
     * @return
     */
    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public Result cancel(@PathVariable("id") Long id) throws Exception {
        orderService.userCancelById(id);
        return Result.success();
    }

//    /**
//     * 再来一单
//     *
//     * @param id
//     * @return
//     */
//    @PostMapping("/repetition/{id}")
//    @ApiOperation("再来一单")
//    public Result repetition(@PathVariable Long id) {
//        orderService.repetition(id);
//        return Result.success();
//    }
//
//    /**
//     * 客户催单
//     * @param id
//     * @return
//     */
//    @GetMapping("/reminder/{id}")
//    @ApiOperation("客户催单")
//    public Result reminder(@PathVariable("id") Long id){
//        orderService.reminder(id);
//        return Result.success();
//    }

    //    /**
//     * 订单支付
//     *
//     * @param ordersPaymentDTO
//     * @return
//     */
//    @PutMapping("/payment")
//    @ApiOperation("订单支付")
//    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
//        log.info("订单支付：{}", ordersPaymentDTO);
//        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
//        log.info("生成预支付交易单：{}", orderPaymentVO);
//        return Result.success(orderPaymentVO);
//    }
}
