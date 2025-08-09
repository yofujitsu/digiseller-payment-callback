package ru.yofujitsu.digiseller_payment_callback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenRequestDto(
        @JsonProperty("seller_id") long sellerId,
        long timestamp,
        String sign
) {
}
