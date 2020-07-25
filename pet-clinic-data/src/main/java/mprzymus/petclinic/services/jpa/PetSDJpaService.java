package mprzymus.petclinic.services.jpa;

import mprzymus.petclinic.model.Pet;
import mprzymus.petclinic.repositories.PetRepository;
import mprzymus.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository repository;

    public PetSDJpaService(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        repository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return repository.save(object);
    }

    @Override
    public void delete(Pet pet) {
        repository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
