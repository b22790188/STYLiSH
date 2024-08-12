package org.example.stylish.dao.recipient;

import jakarta.annotation.Nullable;
import org.example.stylish.dto.orderDto.request.RequestRecipientDto;

import java.math.BigInteger;

public interface RecipientDao {
    @Nullable
    BigInteger insertRecipient(RequestRecipientDto requestRecipientDto);
}
