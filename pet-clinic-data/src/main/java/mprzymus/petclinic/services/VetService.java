package mprzymus.petclinic.services;

import mprzymus.petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findByLastName(String lastName);
    Vet findById(Long id);
    Vet save(Vet owner);
    Set<Vet> findAll();
}
