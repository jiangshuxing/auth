package com.jiangtao.chuandao.module.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户 APP - 商城订单")
@RestController
@RequestMapping("/shop/order")
@Validated
@Slf4j
public class AppShopOrderController {



    @GetMapping("/create")
    @ApiOperation("创建商城订单")
//    @PreAuthenticated // TODO 暂时不加登陆验证，前端暂时没做好
    public String create() {
        // 假装创建商城订单
        Long shopOrderId = System.currentTimeMillis();



        // 拼接返回
        return "创建成功！";
    }

    @GetMapping("/pay-notify")
    @ApiOperation("支付回调")
    public String payNotify() {
        log.info("[payNotify][回调成功]");
        // 拼接返回
        return "创建成功！";
    }

    @GetMapping("/refund-notify")
    @ApiOperation("退款回调")
    public String refundNotify( ) {
        log.info("[refundNotify][回调成功]");
        return "退款回调！";
    }

}
