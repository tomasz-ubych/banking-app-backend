package pl.app.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String pesel;

    @ManyToMany(mappedBy = "customers")
    @JsonIgnore
    private Set<Account> accounts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="customer")
    private Set<Address> addresses = new HashSet<>();

}

