package com.example.demo.controller;

import com.alipay.api.AlipayApiException;
import com.example.demo.bean.AlipayBean;
import com.example.demo.pojo.User;
import com.example.demo.respone.Rep;
import com.example.demo.service.PayService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private UserService userService;


    @CrossOrigin
    @RequestMapping("api/order/alipay")
    public Rep alipay(@RequestBody AlipayBean requestAlipayBean) throws AlipayApiException {
        AlipayBean alipayBean = new AlipayBean();
        String outTradeNo = UUID.randomUUID().toString();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject("口水皮插花教学平台");
        alipayBean.setTotal_amount(requestAlipayBean.getTotal_amount());
        System.out.println(requestAlipayBean.getTotal_amount());
        System.out.println(alipayBean);
        System.out.println(requestAlipayBean.getUsername());
        userService.addCoin(Integer.parseInt(requestAlipayBean.getTotal_amount()),requestAlipayBean.getUsername());
        payService.aliPay(alipayBean);

        return new Rep(200,"ok",payService.aliPay(alipayBean));

    }

}

