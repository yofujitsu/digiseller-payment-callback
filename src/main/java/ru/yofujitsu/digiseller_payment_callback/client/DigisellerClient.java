package ru.yofujitsu.digiseller_payment_callback.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yofujitsu.digiseller_payment_callback.dto.MessageRequestDto;
import ru.yofujitsu.digiseller_payment_callback.dto.TokenRequestDto;
import ru.yofujitsu.digiseller_payment_callback.dto.TokenResponseDto;

@FeignClient(name = "digisellerClient", url = "${digiseller.base-url}")
public interface DigisellerClient {

    @PostMapping("${digiseller.token-path}")
    TokenResponseDto getToken(TokenRequestDto tokenRequestDto);

    @PostMapping("${digiseller.message-path}")
    void sendMessage(
            @RequestBody MessageRequestDto messageRequestDto,
            @RequestParam("token") String token,
            @RequestParam("id_i") long idI
    );
}
