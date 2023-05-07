package com.dna.shop.service;

import com.dna.shop.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> viewsAllOrders();
}
