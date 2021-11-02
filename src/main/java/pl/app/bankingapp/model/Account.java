package pl.app.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String iban;
    @JsonIgnore
    @ManyToMany(cascade={CascadeType.ALL})
    private Set<Customer> customers = new HashSet<>();
    @Column
    private BigDecimal amount;
    @Column
    private Currency currency;
    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public void addItem(Customer item) {
        customers.add(item);
    }
}
