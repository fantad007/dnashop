package com.dnahealth.service;

import com.dnahealth.entity.OrderDetailEntity;
import com.dnahealth.entity.Customer;
import com.dnahealth.entity.OrderEntity;

import java.util.Collection;

public interface ShoppingCartService {
    void add(OrderDetailEntity cart);
    void remove(long id);
    void update(long colorId, int quantity);
    void clear();
    Collection<OrderDetailEntity> getAllCartItems();
    int getCount();
    double getAmount();
    OrderEntity getOrder();
    int getTotalQuantities();
    boolean saveCart(Customer information);
    void sendEmailConfirmOrder(Customer customer);
}
