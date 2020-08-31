package mprzymus.petclinic.controllers;

import lombok.RequiredArgsConstructor;
import mprzymus.petclinic.model.Owner;
import mprzymus.petclinic.model.PetType;
import mprzymus.petclinic.repositories.OwnerRepository;
import mprzymus.petclinic.services.OwnerService;
import mprzymus.petclinic.services.PetService;
import mprzymus.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
@RequiredArgsConstructor
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE = "pets/createOrUpdatePetFor";
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
