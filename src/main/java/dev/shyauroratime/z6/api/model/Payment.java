package dev.shyauroratime.z6.api.model;

import lombok.Data;

@Data
public class Payment {
    private final String userAccount;
    private final double paymentAmount;
    private final String paymentId;

    public Payment(String userAccount, double paymentAmount, String paymentId){
        this.userAccount = userAccount;
        this.paymentAmount = paymentAmount;
        this.paymentId = paymentId;
    }
}
