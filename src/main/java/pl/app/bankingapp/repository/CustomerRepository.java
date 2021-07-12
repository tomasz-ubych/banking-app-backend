package pl.app.bankingapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.app.bankingapp.model.Customer;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
@CrossOrigin
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
