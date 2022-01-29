package pl.app.bankingapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.app.bankingapp.model.Account;
import pl.app.bankingapp.model.AccountType;
import pl.app.bankingapp.model.Transaction;
import pl.app.bankingapp.repository.AccountRepository;
import pl.app.bankingapp.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
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

    @GetMapping(value = "/transaction/receiver", params = "ibanNumber")
    Page<Transaction> getAllReceiverTransactionsByIbanNumber(@RequestParam("ibanNumber") String ibanNumber,
                                                     Pageable pageable) {
        return transactionRepository.findTransactionsByReceiverAccountIban(ibanNumber, pageable);
    }
    @GetMapping(value = "/transaction/sender", params = "ibanNumber")
    Page<Transaction> getAllSenderTransactionsByIbanNumber(@RequestParam("ibanNumber") String ibanNumber,
                                                     Pageable pageable) {
        return transactionRepository.findTransactionsBySenderAccountIban(ibanNumber, pageable);
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestParam String senderAccountIban,
                                         String amount,
                                         String receiverAccountIban,
                                         String currency) {
        System.out.println("SENDER ACCOUNT: " + accountRepository.findByIban(senderAccountIban).toString());
        Account senderAccount = accountRepository.findByIban(senderAccountIban).get(0);
        List<Account> receiverAccounts = accountRepository.findByIban(receiverAccountIban);
        System.out.println("RECEIVER ACCOUNT: " + receiverAccounts.toString());
        Account receiverAccount = new Account();
        if (receiverAccounts.size() > 0){
            receiverAccount = receiverAccounts.get(0);
        }
        else{
            receiverAccount = new Account();
            receiverAccount.setCurrency(Currency.getInstance(currency));
            receiverAccount.setAccountType(AccountType.EXTERNAL);
            receiverAccount.setIban(receiverAccountIban);
            accountRepository.save(receiverAccount);
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(amount));

        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        transactionRepository.save(transaction);
        return transaction;
    }
}
