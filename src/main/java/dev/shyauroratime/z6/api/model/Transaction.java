package dev.shyauroratime.z6.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="transactions")
public class Transaction {
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "type") @Convert(converter = TransactionTypeConverter.class)
    private Enum transactionType;
    @Column(name = "account")
    private String userAccount;
    @Column(name ="is_credit")
    private boolean isCredit;
    @Column(name = "amount")
    private double transactionAmount;
    @Temporal(TemporalType.DATE)
    @Column(name = "date", columnDefinition = "DATE")
    private Date transactionDate;
    public void retrieveDate(){
        final Date dateNow = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final String formattedDate = formatter.format(dateNow);
        try {
            final Date date = formatter.parse(formattedDate);
            this.transactionDate = date;
        } catch (ParseException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
