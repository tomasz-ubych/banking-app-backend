package pl.app.bankingapp.controller;
import org.springframework.web.bind.annotation.*;
import pl.app.bankingapp.model.Customer;
import pl.app.bankingapp.repository.AddressRepository;
import pl.app.bankingapp.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CustomerController {

    private final CustomerRepository repository;
    private final AddressRepository addressRepository;
    CustomerController(CustomerRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository =  addressRepository;
    }
    @GetMapping("/customer")
    List<Customer> one(@RequestParam(required = false) String pesel, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if (pesel != null){
            return repository.findByPesel(pesel);
        }
        else {
            return repository.findByFirstNameAndLastName(firstName, lastName);
        }
    }
    @GetMapping("/customer/{id}")
    Optional<Customer> one(@PathVariable Long id) {
        return repository.findById(id);
    }
    @PostMapping("/customer")
    Customer newEmployee(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

}
