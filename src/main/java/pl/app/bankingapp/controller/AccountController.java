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
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("/account")
    public Account assignCustomerToAccount(@RequestParam(required = false) Long accountId, @RequestParam(required = false) Long customerId){
        Customer customer = customerRepository.findById(customerId).get();
        Account account = accountRepository.findById(accountId).get();
        account.addCustomer(customer);
        return accountRepository.save(account);
    }
}
