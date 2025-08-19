package ru.yofujitsu.digiseller_payment_callback.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhooksService {

    private final DigisellerPaymentService digisellerPaymentService;
    private String MESSAGE = System.getenv("MESSAGE");

    public void handlePayment(long orderId, String email, String amount, String curr) {
        log.info("MESSAGE: {}", MESSAGE.substring(1, MESSAGE.length() - 1));
        log.info("Произошла покупка товара c ID {}, от пользователя с email: {}, на сумму: {} {}", orderId, email, amount, curr);
        digisellerPaymentService.sendMessage(MESSAGE.substring(1, MESSAGE.length() - 1), orderId);
        log.info("Отправил ответное сообщение покупателю.");
    }
}
