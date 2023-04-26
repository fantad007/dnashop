package com.dna.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OrderDto {
    private long orderId;
    private String customerName;
    private Instant createdAt;
    private double totalMoney;
    private String address;
    private String status = "đang chờ xử lý";
}
