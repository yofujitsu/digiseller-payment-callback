package ru.yofujitsu.digiseller_payment_callback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record TokenResponseDto(
        int retval,
        String desc,
        String token,
        @JsonProperty("seller_id") long sellerId,
        @JsonProperty("valid_thru") OffsetDateTime validThru
) {
}
