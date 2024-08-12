package org.example.stylish.service;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dao.color.ColorDao;
import org.example.stylish.dao.junction.JunctionDao;
import org.example.stylish.dao.order.OrderDao;
import org.example.stylish.dao.recipient.RecipientDao;
import org.example.stylish.dao.size.SizeDao;
import org.example.stylish.dao.variant.VariantDao;
import org.example.stylish.dto.orderDto.request.RequestCheckoutDto;
import org.example.stylish.dto.orderDto.request.RequestOrderProductDto;
import org.example.stylish.dto.orderDto.request.RequestRecipientDto;
import org.example.stylish.dto.tapPayDto.CardHolderDto;
import org.example.stylish.dto.tapPayDto.RequestTapPayDto;
import org.example.stylish.exception.VariantNoExistException;
import org.example.stylish.model.Color;
import org.example.stylish.model.Size;
import org.example.stylish.utils.TapPayApiTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
public class OrderService {
    @Autowired
    private VariantDao variantDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RecipientDao recipientDao;

    @Autowired
    private ColorDao colorDao;

    @Autowired
    private SizeDao sizeDao;

    @Autowired
    private JunctionDao junctionDao;

    @Autowired
    private TapPayApiTemplate tapPayApiTemplate;

    public BigInteger checkout(RequestCheckoutDto requestCheckoutDto, BigInteger userId) {
        //todo: using transaction here in here to rollback
        BigInteger recipientId = recipientDao.insertRecipient(requestCheckoutDto.getOrder().getRecipient());

        BigInteger orderId = orderDao.insertOrder(requestCheckoutDto.getOrder(), userId, recipientId);

        List<RequestOrderProductDto> productList = requestCheckoutDto.getOrder().getList();
        for (RequestOrderProductDto product : productList) {
            BigInteger productId = product.getIdAsBigInteger();

            Color color = colorDao.getColor(product.getColor().getCode());
            BigInteger colorId = BigInteger.valueOf(color.getId());

            Size size = sizeDao.getSize(product.getSize());
            BigInteger sizeId = BigInteger.valueOf(size.getId());

            BigInteger variantId;
            Optional<Map<String, Object>> variantOpt = variantDao.getVariantByProductIdColorIdSizeId(productId, colorId, sizeId);
            if (variantOpt.isPresent()) {
                Map<String, Object> variant = variantOpt.get();
                variantId = BigInteger.valueOf(((Number) variant.get("id")).longValue());
                //todo: check quantity here in the future

                boolean isVariant = junctionDao.insertOrderVariant(orderId, variantId, product.getQty());

            } else {
                throw new VariantNoExistException("Variant not found");
            }
        }


        RequestRecipientDto requestRecipientDto = requestCheckoutDto.getOrder().getRecipient();
        CardHolderDto cardHolderDto = CardHolderDto.builder()
            .phoneNumber(requestRecipientDto.getPhone())
            .name(requestRecipientDto.getName())
            .email(requestRecipientDto.getEmail())
            .build();
        RequestTapPayDto requestTapPayDto = RequestTapPayDto.builder()
            //todo: refactor partnerKey and merchantId with env config
            .prime(requestCheckoutDto.getPrime())
            .partnerKey("partner_PHgswvYEk4QY6oy3n8X3CwiQCVQmv91ZcFoD5VrkGFXo8N7BFiLUxzeG")
            .merchantId("AppWorksSchool_CTBC")
            .orderNumber(orderId.toString())
            .details("test")
            .amount(100)
            .cardholder(cardHolderDto)
            .build();

        //todo: using transaction here in the future
        boolean isPaymentSuccess = tapPayApiTemplate.getTapPayResponse(requestTapPayDto);
        boolean isOrderStatusUpdateSuccess = orderDao.updateOrderIsPaid(orderId, isPaymentSuccess);

        if (isPaymentSuccess && isOrderStatusUpdateSuccess) {
            return orderId;
        } else if (isPaymentSuccess && !isOrderStatusUpdateSuccess) {
            throw new RuntimeException("Payment successful but failed to update order");
        } else {
            return BigInteger.valueOf(0);
        }
    }
}
