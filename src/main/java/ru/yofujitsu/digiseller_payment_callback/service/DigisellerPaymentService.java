package ru.yofujitsu.digiseller_payment_callback.service;

import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.yofujitsu.digiseller_payment_callback.client.DigisellerClient;
import ru.yofujitsu.digiseller_payment_callback.dto.MessageRequestDto;
import ru.yofujitsu.digiseller_payment_callback.dto.TokenRequestDto;
import ru.yofujitsu.digiseller_payment_callback.dto.TokenResponseDto;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class DigisellerPaymentService {

    private final DigisellerClient digisellerClient;

    private long SELLER_ID = Long.parseLong(System.getenv("SELLER_ID"));
    private String API_KEY = System.getenv("API_KEY");
    private final String MESSAGE = System.getenv("MESSAGE");
    private final AtomicReference<String> currentToken = new AtomicReference<>();

    public void sendMessage(String message, long orderId) {
        MessageRequestDto messageDto = new MessageRequestDto(
                message,
                null
        );
        digisellerClient.sendMessage(messageDto, currentToken.get(), orderId);
    }

    @Scheduled(fixedRate = 3600000)
    public void scheduleTokenUpdate() {
        updateToken();
    }

    private void updateToken() {
        TokenRequestDto tokenRequestDto = new TokenRequestDto(SELLER_ID,
                System.currentTimeMillis() / 1000,
                generateSign()
        );
        System.out.println(tokenRequestDto.sellerId());
        System.out.println(tokenRequestDto.timestamp());
        System.out.println(tokenRequestDto.sign());

        TokenResponseDto tokenResponseDto = digisellerClient.getToken(tokenRequestDto);
        this.currentToken.set(tokenResponseDto.token());
        log.info("Токен успешно обновлен: {} ", currentToken);
        log.info("MESSAGE: {}", MESSAGE);
    }

    private String generateSign() {
        String sourceString = API_KEY + System.currentTimeMillis() / 1000;
        return Hashing.sha256()
                .hashString(sourceString, StandardCharsets.UTF_8)
                .toString();
    }
}