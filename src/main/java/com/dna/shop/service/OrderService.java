package com.dna.shop.service;

import com.dna.shop.dto.OrderDto;
import com.dna.shop.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    List<OrderDto> viewsAllOrders();
}
