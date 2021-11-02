package pl.app.bankingapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.app.bankingapp.model.Account;
import pl.app.bankingapp.model.Transaction;
import pl.app.bankingapp.repository.AccountRepository;
import pl.app.bankingapp.repository.TransactionRepository;
import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transaction")
    Page<Transaction> getAllTransactionsByIbanNumber(@RequestParam String ibanNumber,
                                                     Pageable pageable) {
        return null;
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestParam String ibanNumber,
                                 @RequestBody Transaction transaction) {
        List<Account> accounts = accountRepository.findByIban(ibanNumber);
        Account account = accounts.get(0);
        Transaction transaction1 = new Transaction();
        transaction.setSenderAccount(account);
        transactionRepository.save(transaction1);
        return transaction1;
    }
}
