package ru.yofujitsu.digiseller_payment_callback.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yofujitsu.digiseller_payment_callback.dto.WebhookResponseDto;
import ru.yofujitsu.digiseller_payment_callback.service.WebhooksService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WebhooksController {

    private final WebhooksService webhooksService;

    @PostMapping("/webhook/sale")
    public ResponseEntity<Void> handlePaymentPost(@RequestBody WebhookResponseDto webhookResponseDto) {
        log.info("Received POST webhook response: {}", webhookResponseDto);
        webhooksService.handlePayment(Long.parseLong(webhookResponseDto.orderId()));
        return ResponseEntity.ok().build();
    }
    @GetMapping("/webhook/sale")
    public ResponseEntity<Void> handlePaymentGet(@RequestParam String id_i,
                                                 @RequestParam String id_d,
                                                 @RequestParam String amount,
                                                 @RequestParam String curr,
                                                 @RequestParam String date,
                                                 @RequestParam String email,
                                                 @RequestParam String sha256,
                                                 @RequestParam String through,
                                                 @RequestParam String ip,
                                                 @RequestParam String agent,
                                                 @RequestParam String cart_uid,
                                                 @RequestParam String isMyProduct) {
        log.info("Received GET webhook response: {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}", id_i, id_d, amount,
                curr, date, email, sha256, ip, agent, cart_uid, isMyProduct);
        webhooksService.handlePayment(Long.parseLong(id_i));
        return ResponseEntity.ok().build();
    }
}
