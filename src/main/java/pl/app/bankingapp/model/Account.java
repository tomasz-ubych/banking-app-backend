package pl.app.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@ToString
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String iban;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name="account_customer",
            joinColumns = @JoinColumn(name="account_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id")
    )
    private Set<Customer> customers = new HashSet<>();
    @Column
    private BigDecimal amount;
    @Column
    private Currency currency;
    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
