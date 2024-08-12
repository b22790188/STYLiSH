package org.example.stylish.dao.order;

import org.example.stylish.dto.orderDto.request.RequestOrderDto;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Map<String, Object>> getAllOrders();

    BigInteger insertOrder(RequestOrderDto requestOrderDto, BigInteger userId, BigInteger recipientId);

    boolean updateOrderIsPaid(BigInteger orderId, boolean isPaid);
}
