package pl.app.bankingapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.app.bankingapp.model.Address;


@CrossOrigin
@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {
    Page<Address> findByCustomerId(Long customerId, Pageable pageable);
}
