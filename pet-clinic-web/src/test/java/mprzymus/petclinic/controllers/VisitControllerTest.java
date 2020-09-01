package mprzymus.petclinic.controllers;

import mprzymus.petclinic.model.Owner;
import mprzymus.petclinic.model.Pet;
import mprzymus.petclinic.model.PetType;
import mprzymus.petclinic.model.Visit;
import mprzymus.petclinic.services.PetService;
import mprzymus.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private MockMvc mockMvc;

    private Pet pet;
    @InjectMocks
    private VisitController tested;

    @Mock
    private VisitService visitService;
    @Mock
    private PetService petService;

    @BeforeEach
    void setUp() {
        pet = Pet.builder()
                .id(1L)
                .birthDate(LocalDate.of(2018,11,13))
                .name("Cutie")
                .visits(new HashSet<>())
                .owner(Owner.builder()
                        .id(1L)
                        .lastName("Doe")
                        .firstName("Joe")
                        .build())
                .petType(PetType.builder()
                        .name("Dog").build())
                .build();
        mockMvc = MockMvcBuilders.standaloneSetup(tested).build();

        when(petService.findById(anyLong())).thenReturn(pet);
    }

    @Test
    void loadPetWithVisitTest() throws Exception {

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("visit"));
    }

    @Test
    void processNewVisitFormTest() throws Exception {
        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("date", "2018-11-11")
                    .param("description", "desc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"))
                .andExpect(model().attributeExists("visit"));

        verify(visitService).save(any());
    }
}