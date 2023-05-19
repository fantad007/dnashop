package com.dnahealth.service;

import com.dnahealth.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> viewsAllOrders();
}
