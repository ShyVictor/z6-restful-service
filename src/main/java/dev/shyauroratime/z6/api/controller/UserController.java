package dev.shyauroratime.z6.api.controller;

import dev.shyauroratime.z6.api.model.Transaction;
import dev.shyauroratime.z6.api.model.TransactionType;
import dev.shyauroratime.z6.api.model.User;
import dev.shyauroratime.z6.api.repository.TransactionRepository;
import dev.shyauroratime.z6.api.repository.UserRepository;
import dev.shyauroratime.z6.api.response.ResponseHandler;
import dev.shyauroratime.z6.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

// Class
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;
    @PostMapping("z6/bank-management/account")
    public ResponseEntity<Object> createUser(@RequestBody User user, @RequestParam(defaultValue = "0", required = false) Double userBalance) {
        final Optional<User> existingUser = userRepository.findByUserAccount(user.getUserAccount());
            if (existingUser.isPresent())
                return ResponseHandler.generateResponse("Esse usuario ja esta cadastrado em nosso banco de dados!", HttpStatus.CONFLICT);
            userService.saveUser(user);
            return ResponseHandler.generateResponse("Cadastro do usuario realizado com sucesso!", HttpStatus.CREATED, user);
    }
    @PatchMapping("z6/bank-management/accounts/{userAccount}/balance")
    public ResponseEntity<Object> addBalanceToUser(@PathVariable String userAccount, @RequestParam(required = true) Double amount){
        final Optional<User> user = userRepository.findByUserAccount(userAccount);
        if (user.isEmpty())
            return ResponseHandler.generateResponse("Usuario nao existe!", HttpStatus.NOT_FOUND);
        user.get().setUserBalance(user.get().getUserBalance() + amount);
        userService.saveUser(user.get());
        return ResponseHandler.generateResponse("Saldo atualizado com suesso. Novo saldo abaixo:", HttpStatus.OK, user.get().getUserBalance());
    }
    @GetMapping("z6/bank/{userAccount}")
    public ResponseEntity<Object> getUserByBalance(@PathVariable String userAccount) {
        final Optional<User> user = userRepository.findByUserAccount(userAccount);
        if(user.isEmpty())
            return ResponseHandler.generateResponse("Essa conta não foi encontrada em nosso banco de dados!", HttpStatus.NOT_FOUND);
        return ResponseHandler.generateResponse("Consulta realizada com sucesso.", HttpStatus.OK, user.get().getUserBalance());
    }
}

