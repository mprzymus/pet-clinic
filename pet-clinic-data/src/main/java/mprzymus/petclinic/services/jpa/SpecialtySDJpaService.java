package mprzymus.petclinic.services.jpa;

import mprzymus.petclinic.model.Specialty;
import mprzymus.petclinic.repositories.SpecialtyRepository;
import mprzymus.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService {
    
    private final SpecialtyRepository repository;

    public SpecialtySDJpaService(SpecialtyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        repository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty specialty) {
        return repository.save(specialty);
    }

    @Override
    public void delete(Specialty specialty) {
        repository.delete(specialty);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
