package ru.yofujitsu.digiseller_payment_callback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilesDto(
        @JsonProperty("newid") String newId,
        String name,
        String type
) {
}
