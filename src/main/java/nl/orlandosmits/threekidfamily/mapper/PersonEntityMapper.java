package nl.orlandosmits.threekidfamily.mapper;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import nl.orlandosmits.threekidfamily.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityMapper {

    private final PersonRepository personRepository;

    public PersonEntityMapper(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonEntity mapFrom(Person person) {
        PersonEntity entity = personRepository.findById(person.getId())
                .orElse(new PersonEntity(person.getId()));

        entity.setName(person.getName());
        entity.setBirthDate(person.getBirthDate());

        if (person.hasPartner()) {
            entity.setPartnerId(person.getPartner().getId());
        }

        if (person.hasParent1()) {
            entity.setParent1Id(person.getParent1().getId());
        }

        if (person.hasParent2()) {
            entity.setParent2Id(person.getParent2().getId());
        }

        if (person.hasChildren()) {
            entity.setChildrenIds(person.getChildrenIds());
        }

        return entity;
    }
}
