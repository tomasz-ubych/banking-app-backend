package pl.app.bankingapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.app.bankingapp.model.Account;
import pl.app.bankingapp.model.Address;


@CrossOrigin
public interface AddressRepository  extends JpaRepository<Address, Long> {
    Page<Address> findByCustomerId(Long customerId, Pageable pageable);
}
