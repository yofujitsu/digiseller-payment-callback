package ru.yofujitsu.digiseller_payment_callback.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhooksService {

    private final DigisellerPaymentService digisellerPaymentService;
    private static final String MESSAGE = "\uD83D\uDD25Благодарю за покупку\uD83D\uDD25\n" +
            "Оставьте, пожалуйста, положительный отзыв - это очень поможет развитию моего магазина\n" +
            "\uD83C\uDF81В качестве подарка за отзыв предлагаю ключ от случайной игры в Steam \uD83C\uDF81";

    public void handlePayment(long orderId) {
        log.info("Произошла покупка товара {}.", orderId);
        digisellerPaymentService.sendMessage(MESSAGE, orderId);
        log.info("Отправил ответное сообщение покупателю.");
    }
}
