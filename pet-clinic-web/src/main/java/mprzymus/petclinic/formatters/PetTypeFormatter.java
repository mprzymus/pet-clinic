package mprzymus.petclinic.formatters;

import lombok.RequiredArgsConstructor;
import mprzymus.petclinic.model.PetType;
import mprzymus.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;
    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
        return findPetTypes.stream().filter(petType -> petType.getName().equals(s))
                .findAny().orElseThrow(() -> new ParseException("type not found", 0));
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
