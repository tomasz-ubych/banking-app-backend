package pl.app.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.app.bankingapp.model.Customer;

import java.util.List;

@CrossOrigin
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByPesel(@Param("pesel") String pesel);
    List<Customer> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
