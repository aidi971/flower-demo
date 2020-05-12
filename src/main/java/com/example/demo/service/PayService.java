package com.example.demo.service;

import com.alipay.api.AlipayApiException;
import com.example.demo.bean.AlipayBean;
import com.example.demo.pojo.Alipays;

public interface PayService {
    /**
     * 支付宝支付接口
     * @param
     * @return
     */
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}
