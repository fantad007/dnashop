package com.dnahealth.service.impl;

import com.dnahealth.code.OrderCodes;
import com.dnahealth.entity.OrderDetailEntity;
import com.dnahealth.entity.Customer;
import com.dnahealth.entity.OrderEntity;
import com.dnahealth.repository.CustomerRepository;
import com.dnahealth.repository.OrderDetailRepository;
import com.dnahealth.repository.OrderRepository;
import com.dnahealth.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.mail.internet.MimeMessage;
import java.util.*;

@SessionScope
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String fromEmailAddress;
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
    public OrderEntity getOrder() {
        orderEntity.setOrderDetails(getAllCartItems());
        orderEntity.setTotalMoney(getAmount() + getAmount()*0.1);
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
            OrderEntity orderEntity = getOrder();
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
            sendEmailConfirmOrder(information);
            clear();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void sendEmailConfirmOrder(Customer customer) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            String subject = "DNAHEALTH - BẠN ĐÃ ĐẶT HÀNG THÀNH CÔNG";
            String content = "<div>\n" +
                    "        Xin chào <strong>[[name]]</strong>,\n" +
                    "        <br> Chúc mừng bạn đã đặt hàng thành công sản phẩm của DNAHEALTH " +
                    "        <br> Đơn hàng của bạn sẽ được chúng tôi xử lý và giao tận tay bạn sớm nhất\n" +
                    "        <br> Chúc bạn có một ngày thật nhiều năng lượng và niềm vui!\n" +
                    "        Xin cảm ơn,\n" +
                    "        <br> -- DNAHEALTH --\n" +
                    "    </div>";
            helper.setFrom(fromEmailAddress);
            helper.setTo(customer.getEmail());
            helper.setSubject(subject);
            content = content.replace("[[name]]", customer.getName());
            helper.setText("<html><body>" + content + "</html></body>", true);
            mailSender.send(message);
            log.info("Gửi Mail Thành Công...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
