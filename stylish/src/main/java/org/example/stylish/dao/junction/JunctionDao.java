package org.example.stylish.dao.junction;

import org.example.stylish.dto.ColorDto;

import java.math.BigInteger;
import java.util.List;

public interface JunctionDao {
    String insertProductIdAndSizeId(Long productId, Long sizeId);

    String insertProductIdAndColorId(Long productId, Long colorId);

    boolean insertOrderVariant(BigInteger orderId, BigInteger variantId, int quantity);

    List<ColorDto> getColorsByProductId(long productId);

    List<String> getSizesByProductId(long productId);
}
