package mprzymus.petclinic.services.jpa;

import mprzymus.petclinic.model.Owner;
import mprzymus.petclinic.repositories.OwnerRepository;
import mprzymus.petclinic.repositories.PetRepository;
import mprzymus.petclinic.repositories.PetTypeRepository;
import mprzymus.petclinic.services.OwnerService;
import mprzymus.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerSDJpaService service;
    public static final long OWNER_ID = 1L;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        final String surname = "Smith";
        Owner owner = Owner.builder().id(OWNER_ID).lastName(surname).build();
        when(ownerRepository.findAllByLastName(surname)).thenReturn(owner);

        Owner smith = service.findByLastName(surname);

        assertEquals(surname, smith.getLastName());

        verify(ownerRepository).findAllByLastName(any());
    }

    @Test
    void findAll() {
        var owner1 = buildOwner(1L);
        var owner2 = buildOwner(2L);
        var owners = new HashSet<Owner>();
        owners.add(owner1);
        owners.add(owner2);
        when(ownerRepository.findAll()).thenReturn(owners);

        var findAllResult = service.findAll();

        assertNotNull(findAllResult);
        assertEquals(owners.size(), findAllResult.size());
    }

    @Test
    void findById() {
        var owner = buildOwner(OWNER_ID);
        when(ownerRepository.findById(OWNER_ID)).thenReturn(Optional.of(owner));

        var result = service.findById(OWNER_ID);
        assertEquals(owner.getId(), result.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());
        var result = service.findById(OWNER_ID);
        assertNull(result);
    }

    @Test
    void save() {
        var toSave = buildOwner(OWNER_ID);
        when(ownerRepository.save(toSave)).thenReturn(toSave);
        var saved = service.save(toSave);


        assertEquals(toSave.getId(), saved.getId());
        
    }

    @Test
    void delete() {
        var toDelete = buildOwner(OWNER_ID);
        service.delete(toDelete);
        verify(ownerRepository).delete(toDelete);
    }

    @Test
    void deleteById() {
        service.deleteById(OWNER_ID);
        verify(ownerRepository).deleteById(OWNER_ID);
    }
    
    private Owner buildOwner(long ownerId) {
        return Owner.builder().id(ownerId).build();
    }
}