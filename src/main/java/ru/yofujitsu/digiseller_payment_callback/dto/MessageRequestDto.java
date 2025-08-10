package ru.yofujitsu.digiseller_payment_callback.dto;


import java.util.List;

public record MessageRequestDto(
        String message,
        List<FilesDto> files
) {
}
