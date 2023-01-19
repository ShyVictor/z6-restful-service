package dev.shyauroratime.z6.api.controller;

import dev.shyauroratime.z6.api.model.User;
import dev.shyauroratime.z6.api.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PaymentController {
    @PostMapping("payments")
    public ResponseEntity<Object> renderPayment(@RequestBody String body) {
        System.out.println(body);
        return ResponseHandler.generateResponse("Pagamento recebido!", HttpStatus.OK);
    }
}
