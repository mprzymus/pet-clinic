package mprzymus.petclinic.controllers;

import lombok.RequiredArgsConstructor;
import mprzymus.petclinic.model.Owner;
import mprzymus.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
@RequiredArgsConstructor
public class OwnersController {

    private final OwnerService ownerService;

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> ownersWithProvidedLastName = ownerService.findAllByLastNameLike(owner.getLastName());
        if (ownersWithProvidedLastName.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if (ownersWithProvidedLastName.size() == 1) {
            owner = ownersWithProvidedLastName.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }
        else {
            model.addAttribute("selections", ownersWithProvidedLastName);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwners(@PathVariable Long ownerId) {
        ModelAndView mov = new ModelAndView("owners/ownerDetails");
        mov.addObject(ownerService.findById(ownerId));
        return mov;
    }
}
