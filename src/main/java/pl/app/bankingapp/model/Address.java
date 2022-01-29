package pl.app.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.app.bankingapp.model.Customer;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String postalCode;
    @Column
    private String apartmentNumber;
    @Column
    private String flatNumber;
    @Column
    private String country;
    @Column
    private String province;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", referencedColumnName="id")
    private Customer customer;



    public void assignCustomer(Customer customer){
        this.customer = customer;
    }

}
