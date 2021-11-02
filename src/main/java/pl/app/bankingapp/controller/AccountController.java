package pl.app.bankingapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.app.bankingapp.model.Account;
import pl.app.bankingapp.model.Address;
import pl.app.bankingapp.model.Customer;
import pl.app.bankingapp.repository.AccountRepository;
import pl.app.bankingapp.repository.AddressRepository;
import pl.app.bankingapp.repository.CustomerRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
public class AccountController {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    AccountController(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/account")
    Page<Account> getAllAccountsByCustomerId(@RequestParam Long customerId,
                                             Pageable pageable) {
        return accountRepository.findByCustomersId(customerId, pageable);
    }

    @PostMapping("/account")
    public Account createAccount(@RequestParam Long customerId,
                                  @RequestBody Account account) {
        Optional<Customer> customers = customerRepository.findById(customerId);
        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        customers.map(customer -> {
            customer.setAccounts(new HashSet<Account>(accounts));
            account.addItem(customer);
            accountRepository.save(account);
            customerRepository.save(customer);
            return account;
        }).orElseThrow(() -> new ResourceNotFoundException("CustomerId " + customerId + " not found"));
        return account;
    }




}
