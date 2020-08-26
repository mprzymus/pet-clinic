package mprzymus.petclinic.controllers;

import mprzymus.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnersController {

    private final OwnerService ownerService;

    public OwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({"/find"})
    public String findOwners() {
        return "notimplemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwners(@PathVariable Long ownerId) {
        ModelAndView mov = new ModelAndView("owners/ownerDetails");
        mov.addObject(ownerService.findById(ownerId));
        return mov;
    }
}
