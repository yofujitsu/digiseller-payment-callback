package ru.yofujitsu.digiseller_payment_callback.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhooksService {

    private final DigisellerPaymentService digisellerPaymentService;
    private final String MESSAGE = System.getenv("MESSAGE");

    public void handlePayment(long orderId, String email, long amount, String curr) {
        log.info("Произошла покупка товара c ID {}, от пользователя с email: {}, на сумму: {} {}", orderId, email, amount, curr);
        digisellerPaymentService.sendMessage(MESSAGE, orderId);
        log.info("Отправил ответное сообщение покупателю.");
    }
}
