package com.dna.shop.service;

import com.dna.shop.entity.OrderDetailEntity;
import com.dna.shop.entity.Customer;
import com.dna.shop.entity.OrderEntity;

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
}
