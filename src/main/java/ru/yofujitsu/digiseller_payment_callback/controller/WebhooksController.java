package ru.yofujitsu.digiseller_payment_callback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yofujitsu.digiseller_payment_callback.dto.WebhookResponseDto;
import ru.yofujitsu.digiseller_payment_callback.service.WebhooksService;

@RestController
@RequiredArgsConstructor
public class WebhooksController {

    private final WebhooksService webhooksService;

    @PostMapping("/webhook/sale")
    public ResponseEntity<Void> handlePaymentPost(@RequestBody WebhookResponseDto webhookResponseDto) {
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
        webhooksService.handlePayment(Long.parseLong(id_i));
        return ResponseEntity.ok().build();
    }
}
