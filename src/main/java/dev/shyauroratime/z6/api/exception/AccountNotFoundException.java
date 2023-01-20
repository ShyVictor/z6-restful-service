package dev.shyauroratime.z6.api.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountId) {
        super("Conta não encontrada em nosso banco de dados! " + accountId);
    }
}
