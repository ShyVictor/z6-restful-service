package dev.shyauroratime.z6.api.repository;

import dev.shyauroratime.z6.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
