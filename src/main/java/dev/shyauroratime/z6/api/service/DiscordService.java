package dev.shyauroratime.z6.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class DiscordService {

    @Autowired
    private final RestTemplate restTemplate;

    public DiscordService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void logTransactionToDiscord(String userAccount, double amount, String transactionType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("content", "[**Z6 BANK** <:dinheiro:967223771800952852>] A conta de ID **"+userAccount+"** (<@"+userAccount+"> realizou uma transação do tipo **"+transactionType.toUpperCase()+"**. " +
                "Seu valor foi de: R$ "+String.valueOf(amount).replace(".",",")+" reais.");
        requestBody.put("username", "Z6 Bank");
        requestBody.put("avatar_url", "https://cdn.discordapp.com/attachments/1050025781700612129/1066945360888602694/bmV5LnBuZw.png");
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        final String BASE_URL = "https://discord.com/api/webhooks/1066943518729326623/EUnp-L2wmmARFLHl3UOsQdhg61ciN_cEss6JvmmqohosJ479gcl7302vHtfcFFRisdqw";
        restTemplate.postForObject(BASE_URL, request, String.class);
    }
}
