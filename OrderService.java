package com.example.ecom.service;

import com.example.ecom.model.*;
import com.example.ecom.repo.OrderItemRepository;
import com.example.ecom.repo.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;

    public OrderService(OrderRepository orderRepo, OrderItemRepository itemRepo) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
    }

    public Order placeOrder(User user, Cart cart) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus("PLACED");
        order.setTotalAmount(cart.getTotal());
        order.setItems(new ArrayList<>());
        Order saved = orderRepo.save(order);

        for (CartItem ci : cart.getItems().values()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(saved);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setUnitPrice(ci.getProduct().getPrice());
            itemRepo.save(oi);
        }
        return saved;
    }
}
