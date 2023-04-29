package com.dna.shop.service.impl;

import com.dna.shop.entity.CartEntity;
import com.dna.shop.entity.OrderDetail;
import com.dna.shop.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Long, CartEntity> map = new HashMap<>();
    OrderDetail orderDetail = new OrderDetail();

    @Override
    public void add(CartEntity cart) {
        long productId = cart.getProduct().getId();
        CartEntity cartItem = map.get(productId);
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
        CartEntity cartItem = map.get(productId);
        cartItem.setQuantity(quantity);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<CartEntity> getAllCartItems() {
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
    public OrderDetail getOrderDetail() {
        orderDetail.setCart(getAllCartItems());
        orderDetail.setTotalMoney(getAmount());
        return orderDetail;
    }

    @Override
    public int getTotalQuantities() {
        int totalQuantities = 0;
        Collection<CartEntity> listCart = getAllCartItems();
        for (CartEntity cart : listCart) {
            totalQuantities += cart.getQuantity();
        }
        return totalQuantities;
    }
}
