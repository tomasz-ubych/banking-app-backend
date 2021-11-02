package pl.app.bankingapp.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.bankingapp.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findTransactionsBy(String accountIban, Pageable pageable);
}
