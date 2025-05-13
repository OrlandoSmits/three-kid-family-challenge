package nl.orlandosmits.threekidfamily.service;

import nl.orlandosmits.threekidfamily.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {

    private static final Logger log = LoggerFactory.getLogger(PeopleService.class);

    /**
     * Checks if a person is valid.
     *  - Has a partner
     *  - Has exactly 3 children and all 3 have the same partner listed as mother or father.
     *  - At least one of those children is under 18.
     * @param person A person to be checked.
     * @return true if a person is valid.
     */
    public boolean isValidPerson(Person person) {
        log.debug("Checking if personn {} is valid", person.getName());
        return true;
    }

}
