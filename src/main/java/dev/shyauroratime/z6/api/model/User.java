package dev.shyauroratime.z6.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="accounts")
public class User {
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String userAccount;
    @Column(name = "balance")
    private double userBalance;

    public Double addBalanceAmount(Double amount) {
        final Double newBalance = this.getUserBalance() + amount;
        this.userBalance = newBalance;
        return newBalance;
}
    }
