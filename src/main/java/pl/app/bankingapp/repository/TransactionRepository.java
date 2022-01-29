package pl.app.bankingapp.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.bankingapp.model.Account;
import pl.app.bankingapp.model.Address;
import pl.app.bankingapp.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findTransactionsByReceiverAccountIban(String accountIban, Pageable pageable);
    Page<Transaction> findTransactionsBySenderAccountIban(String accountIban, Pageable pageable);
}
