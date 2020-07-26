package mprzymus.petclinic.services.jpa;

import mprzymus.petclinic.model.Visit;
import mprzymus.petclinic.repositories.VisitRepository;
import mprzymus.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {
    private final VisitRepository repository;

    public VisitSDJpaService(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Visit> findAll() {
        var visits = new HashSet<Visit>();
        repository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {
        return repository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        repository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
