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

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

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

    public User
    updateUserBalance(User user,
                     Long id)
    {
        User depDB = userRepository.findById(id).get();

        if (Objects.nonNull(user.getUserBalance())) {
            depDB.setUserBalance(user.getUserBalance());
        }


        return userRepository.save(depDB);
    }

    public List<User> fetchDepartmentList()
    {
        return (List<User>)
                userRepository.findAll();
    }
}