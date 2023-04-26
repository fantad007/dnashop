package com.dna.shop.service;

import com.dna.shop.entity.CartEntity;
import com.dna.shop.entity.OrderDetail;

import java.util.Collection;

public interface ShoppingCartService {
    void add(CartEntity cart);
    void remove(long id);
    void update(long colorId, int quantity);
    void clear();
    Collection<CartEntity> getAllCartItems();
    int getCount();
    double getAmount();
    OrderDetail getOrderDetail();
    int getTotalQuantities();
}
