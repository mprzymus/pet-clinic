package mprzymus.petclinic.repositories;

import mprzymus.petclinic.model.Owner;
import mprzymus.petclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findAllByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
