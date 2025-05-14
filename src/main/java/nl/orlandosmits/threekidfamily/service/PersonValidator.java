package nl.orlandosmits.threekidfamily.service;

import nl.orlandosmits.threekidfamily.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {
    
    public boolean hasPartner(Person person) {
        if (person == null) {
            return false;
        }

        return person.hasPartner();
    }

    public boolean hasThreeChildrenWithPartnerAsParent(Person person) {
        if (person == null) {
            return false;
        }

        if (!person.hasExactlyThreeChildren()) {
            return false;
        }

        return person.allThreeChildrenHavePartnerAsMotherOrFather();
    }

    public boolean hasOneChildUnderEighteen(Person person) {
        if (person == null) {
            return false;
        }

        return person.oneChildIsUnderEighteen();
    }

}
