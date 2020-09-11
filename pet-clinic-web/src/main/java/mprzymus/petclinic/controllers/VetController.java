package mprzymus.petclinic.controllers;

import mprzymus.petclinic.model.Person;
import mprzymus.petclinic.model.Vet;
import mprzymus.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets.html", "/vets", "/vets.index", "/vets.index.html"})
    public String listVets(Model model) {
        var sortedVets = vetService.findAll().stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());
        model.addAttribute("vets", sortedVets);
        return "vets/index";
    }
}
