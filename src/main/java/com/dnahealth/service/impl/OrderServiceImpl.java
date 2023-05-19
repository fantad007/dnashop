package com.dnahealth.service.impl;

import com.dnahealth.dto.OrderDto;
import com.dnahealth.entity.Customer;
import com.dnahealth.entity.OrderEntity;
import com.dnahealth.repository.CustomerRepository;
import com.dnahealth.repository.OrderRepository;
import com.dnahealth.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderDetailRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<OrderDto> viewsAllOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<OrderEntity> orderList = orderDetailRepository.findAll();
        if (!orderList.isEmpty()) {
            for (OrderEntity orderEntity : orderList) {
                int customerId = orderEntity.getCustomer().getId();
                if (customerRepository.findById(customerId).isPresent()) {
                    Customer customer = customerRepository.findById(customerId).get();
                    OrderDto orderDto = new OrderDto();
                    orderDto.setOrderId(orderEntity.getId());
                    orderDto.setCustomerName(customer.getName());
                    orderDto.setCreatedAt(orderEntity.getCreatedAt());
                    orderDto.setTotalMoney(orderEntity.getTotalMoney());
                    orderDto.setAddress(customer.getAddress());
                    orderDtoList.add(orderDto);
                }
            }
        }
        return orderDtoList;
    }
}
