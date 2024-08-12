package org.example.stylish.dao.order;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.orderDto.request.RequestOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllOrders() {
        try {
            String sql = "SELECT * FROM `report`";
            return jdbcTemplate.queryForList(sql);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    public BigInteger insertOrder(RequestOrderDto requestOrderDto, BigInteger userId, BigInteger recipientId) {
        try {
            String sql = "INSERT INTO `order` (`shipping`, `payment`, `subtotal`, `freight`, `total`, `isPaid`, `user_id`, `recipient_id`) VALUES (?,?,?,?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            BigDecimal bigDecimalUserId = new BigDecimal(userId);
            BigDecimal bigDecimalRecipientId = new BigDecimal(recipientId);
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, requestOrderDto.getShipping());
                ps.setString(2, requestOrderDto.getPayment());
                ps.setInt(3, requestOrderDto.getSubtotal());
                ps.setInt(4, requestOrderDto.getFreight());
                ps.setInt(5, requestOrderDto.getTotal());
                // status 0 means unpaid
                ps.setInt(6, 0);
                ps.setBigDecimal(7, bigDecimalUserId);
                ps.setBigDecimal(8, bigDecimalRecipientId);
                return ps;
            }, keyHolder);

            return BigInteger.valueOf(keyHolder.getKey().longValue());

        } catch (NullPointerException npe) {
            log.error(npe.getMessage());
            throw npe;
        }
    }

    public boolean updateOrderIsPaid(BigInteger orderId, boolean isPaid) {
        try {
            String sql = "UPDATE `order` set `isPaid` = ? where `id` = ?";
            int isPaidStatus = isPaid ? 1 : 2;
            jdbcTemplate.update(sql, isPaidStatus, orderId);
            return true;
        } catch (DataAccessException dae) {
            log.error(dae.getMessage());
            return false;
        }
    }
}
