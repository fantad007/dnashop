package com.dna.shop.service.impl;

import com.dna.shop.code.OrderCodes;
import com.dna.shop.entity.OrderDetailEntity;
import com.dna.shop.entity.Customer;
import com.dna.shop.entity.OrderEntity;
import com.dna.shop.repository.CustomerRepository;
import com.dna.shop.repository.OrderDetailRepository;
import com.dna.shop.repository.OrderRepository;
import com.dna.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    Map<Long, OrderDetailEntity> map = new HashMap<>();
    OrderEntity orderEntity = new OrderEntity();

    @Override
    public void add(OrderDetailEntity cart) {
        long productId = cart.getProduct().getId();
        OrderDetailEntity cartItem = map.get(productId);
        if (cartItem == null) {
            map.put(productId, cart);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }

    @Override
    public void remove(long id) {
        map.remove(id);
    }

    @Override
    public void update(long productId, int quantity) {
        OrderDetailEntity cartItem = map.get(productId);
        cartItem.setQuantity(quantity);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<OrderDetailEntity> getAllCartItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.values().size();
    }

    @Override
    public double getAmount() {
        return map.values().stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
    }

    @Override
    public OrderEntity getOrderDetail() {
        orderEntity.setOrderDetails(getAllCartItems());
        orderEntity.setTotalMoney(getAmount());
        return orderEntity;
    }

    @Override
    public int getTotalQuantities() {
        int totalQuantities = 0;
        Collection<OrderDetailEntity> listCart = getAllCartItems();
        for (OrderDetailEntity cart : listCart) {
            totalQuantities += cart.getQuantity();
        }
        return totalQuantities;
    }

    @Override
    public boolean saveCart(Customer information) {
        try {
            OrderEntity orderEntity = getOrderDetail();
            Collection<OrderEntity> orderEntities = new ArrayList<>();
            orderEntities.add(orderEntity);
            information.setOrderEntities(orderEntities);
            customerRepository.save(information);
            orderEntity.setCustomer(information);
            orderEntity.setStatus(OrderCodes.StatusFlag.STAGE_ONE.getCode());
            orderRepository.save(orderEntity);
            for (OrderDetailEntity orderDetail : orderEntity.getOrderDetails()) {
                orderDetail.setOrder(orderEntity);
                orderDetailRepository.save(orderDetail);
            }
            clear();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
