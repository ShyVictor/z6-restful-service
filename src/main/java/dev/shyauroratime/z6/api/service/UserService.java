package dev.shyauroratime.z6.api.service;

import dev.shyauroratime.z6.api.model.Transaction;
import dev.shyauroratime.z6.api.model.TransactionType;
import dev.shyauroratime.z6.api.model.User;
import dev.shyauroratime.z6.api.repository.TransactionRepository;
import dev.shyauroratime.z6.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private DiscordService discordService;

    public User saveUser(User user)
    {
        final Transaction transaction = new Transaction().builder()
                .userAccount(user.getUserAccount())
                .isCredit(false)
                .transactionAmount(0)
                .transactionType(TransactionType.CREATED)
                .build();
        transaction.retrieveDate();

        transactionRepository.save(transaction);
        return userRepository.save(user);
    }
    public Optional<User> findByUserAccount(String userAccount){
        return userRepository.findByUserAccount(userAccount);
    }
    public void deleteUserByUserAccount(User user){
        userRepository.delete(user);
    }
    public User updateUserBalance(User user, double updatedBalance) {
        final Transaction transaction = new Transaction().builder()
                .userAccount(user.getUserAccount())
                .isCredit(true)
                .transactionAmount(updatedBalance)
                .transactionType(updatedBalance > 0 ? TransactionType.DEPOSIT : TransactionType.SHOP)
                .build();
        transaction.retrieveDate();
        transactionRepository.save(transaction);
        discordService.logTransactionToDiscord(user.getUserAccount(), updatedBalance, updatedBalance > 0 ? TransactionType.DEPOSIT.getValue() : TransactionType.SHOP.getValue());
        return userRepository.save(user);
    }

}