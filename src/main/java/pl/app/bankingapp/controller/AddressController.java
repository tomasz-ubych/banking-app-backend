package pl.app.bankingapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.app.bankingapp.model.Account;
import pl.app.bankingapp.model.Address;
import pl.app.bankingapp.model.Customer;
import pl.app.bankingapp.repository.AddressRepository;
import pl.app.bankingapp.repository.CustomerRepository;
import java.util.List;

@CrossOrigin
@RestController
public class AddressController {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    AddressController(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/address")
    Page<Address> getAllAddressesByCustomerId(@RequestParam Long customerId ,
                                              Pageable pageable) {
        return addressRepository.findByCustomerId(customerId, pageable);
    }
    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @PutMapping("/address")
    public Address assignAddressToCustomer(@RequestParam(required = false) Long customerId, @RequestParam(required = false) Long addressId){
        Customer customer = customerRepository.findById(customerId).get();
        System.out.println("is customer found: " + customer.getId());
        Address address = addressRepository.findById(addressId).get();
        address.assignCustomer(customer);
        return addressRepository.save(address);
    }
}
