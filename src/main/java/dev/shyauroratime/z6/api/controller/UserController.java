package dev.shyauroratime.z6.api.controller;

import dev.shyauroratime.z6.api.exception.AccountAlreadyExistException;
import dev.shyauroratime.z6.api.exception.AccountNotFoundException;
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
    private UserService userService;
    @PostMapping("z6/bank-management/accounts")
    public ResponseEntity<Object> createUser(@RequestParam(required = true) String userAccount, @RequestParam(defaultValue = "0", required = false) Double userBalance) {
        final Optional<User> existingUser = userService.findByUserAccount(userAccount);
            if (existingUser.isPresent())
                throw new AccountAlreadyExistException(userAccount);
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserBalance(userBalance != null ? userBalance : 0);
            userService.saveUser(user);
            return ResponseHandler.generateResponse("Cadastro do usuario realizado com sucesso!", HttpStatus.CREATED);
    }
    @PatchMapping("z6/bank-management/accounts/{userAccount}/balance")
    public ResponseEntity<Object> addBalanceToUser(@PathVariable String userAccount, @RequestParam(required = true) Double amount){
        final User user = userService.findByUserAccount(userAccount)
                .orElseThrow(() -> new AccountNotFoundException(userAccount));
        final Double updatedBalance = user.addBalanceAmount(amount);
        userService.updateUserBalance(user, amount, updatedBalance);
        return ResponseHandler.generateResponse("Saldo atualizado com sucesso. Novo saldo abaixo:", HttpStatus.OK, user.getUserBalance());
    }
    @GetMapping("z6/bank-management/accounts/{userAccount}/balance")
    public ResponseEntity<Object> getUserByBalance(@PathVariable String userAccount) {
        final User user = userService.findByUserAccount(userAccount)
                .orElseThrow(() -> new AccountNotFoundException(userAccount));
        return ResponseHandler.generateResponse("Consulta realizada com sucesso.", HttpStatus.OK, user.getUserBalance());
    }

    @DeleteMapping("z6/bank-management/accounts")
    public ResponseEntity<Object> deleteByUserAccount(@RequestParam(required = true) String userAccount){
        final User user = userService.findByUserAccount(userAccount)
                .orElseThrow(() -> new AccountNotFoundException(userAccount));
        userService.deleteUserByUserAccount(user);
        return ResponseHandler.generateResponse("Usuario deletado com sucesso!", HttpStatus.OK);

    }
}

