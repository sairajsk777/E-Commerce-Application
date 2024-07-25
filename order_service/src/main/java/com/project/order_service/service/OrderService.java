package com.project.order_service.service;

import com.project.order_service.dto.OrderLineItemsDto;
import com.project.order_service.dto.OrderRequest;
import com.project.order_service.model.Order;
import com.project.order_service.model.OrderLineItems;
import com.project.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToOrderLineItem).toList());
        orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItem(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder().id(orderLineItemsDto.getId())
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity()).build();
    }
}
