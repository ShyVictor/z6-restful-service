package dev.shyauroratime.z6.api.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String accountId) {
        super("Essa conta ja esta cadastrada em nosso banco de dados! " + accountId);
    }
    }
