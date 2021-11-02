package pl.app.bankingapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.app.bankingapp.model.Account;

import java.util.List;

@CrossOrigin
public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findByCustomersId(Long customerId, Pageable pageable);
    List<Account> findByIban(@Param("iban") String iban);



}
