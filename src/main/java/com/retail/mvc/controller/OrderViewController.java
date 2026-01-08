package com.retail.mvc.controller;

import com.retail.mvc.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderViewController {

    @Autowired
    private OrderClient orderClient;
}