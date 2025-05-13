package nl.orlandosmits.threekidfamily.service;

import lombok.extern.slf4j.Slf4j;
import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.mapper.PersonMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PeopleService {

    private final PersonMapper personMapper;

    public PeopleService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    /**
     * Checks if a person is valid. - Has a partner - Has exactly 3 children and all 3 have the same partner listed as
     * mother or father. - At least one of those children is under 18.
     *
     * @param person A person to be checked.
     * @return true if a person is valid.
     */
    public boolean isValidPerson(Person person) {
        if (person == null) {
            return false;
        }

        if (!person.hasPartner()) {
            log.warn("Person {} has no partner", person.getName());
            return false;
        }

        return true;
    }

    public Person getPerson(PeopleRequestDto peopleRequestDto) {
        return personMapper.mapFrom(peopleRequestDto);
    }
}
