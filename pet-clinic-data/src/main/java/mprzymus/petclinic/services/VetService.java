package mprzymus.petclinic.services;

import mprzymus.petclinic.model.Vet;

public interface VetService extends CrudService<Vet, Long> {
    Vet findByLastName(String lastName);
}
