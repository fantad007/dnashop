package com.dna.shop.service.impl;

import com.dna.shop.dto.OrderDto;
import com.dna.shop.entity.Customer;
import com.dna.shop.entity.OrderDetail;
import com.dna.shop.repository.CustomerRepository;
import com.dna.shop.repository.OrderDetailRepository;
import com.dna.shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<OrderDto> viewsAllOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<OrderDetail> orderList = orderDetailRepository.findAll();
        if (!orderList.isEmpty()) {
            for (OrderDetail orderDetail: orderList) {
                int customerId = orderDetail.getCustomer().getId();
                if (customerRepository.findById(customerId).isPresent()) {
                    Customer customer = customerRepository.findById(customerId).get();
                    OrderDto orderDto = new OrderDto();
                    orderDto.setOrderId(orderDetail.getId());
                    orderDto.setCustomerName(customer.getName());
                    orderDto.setCreatedAt(orderDetail.getCreatedAt());
                    orderDto.setTotalMoney(orderDetail.getTotalMoney());
                    orderDto.setAddress(customer.getAddress());
                    orderDtoList.add(orderDto);
                }
            }
        }
        return orderDtoList;
    }
}
