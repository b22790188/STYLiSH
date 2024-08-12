package org.example.stylish.dao.variant;

import org.example.stylish.dto.VariantDto;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VariantDao {

    Integer insertVariant(long productId, long colorId, long sizeId, int stock);

    Optional<Map<String, Object>> getVariantByProductIdColorIdSizeId(BigInteger productId, BigInteger colorId, BigInteger size);

    List<VariantDto> getVariantsByProductId(long productId);
}
