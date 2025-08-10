package ru.yofujitsu.digiseller_payment_callback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebhookResponseDto(
        @JsonProperty("ID_I") String orderId,
        @JsonProperty("ID_D") String productId,
        @JsonProperty("Amount") String amount,
        @JsonProperty("Currency") String currency,
        @JsonProperty("Email") String email,
        @JsonProperty("Date") String date,
        @JsonProperty("SHA256") String sha256,
        @JsonProperty("Through") String through,
        @JsonProperty("IP") String ip,
        @JsonProperty("Agent") String agent,
        @JsonProperty("CartUID") String cartUid,
        @JsonProperty("IsMyProduct") String isMyProduct
) {
}
