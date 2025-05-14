package nl.orlandosmits.threekidfamily.mapper;

import java.util.Optional;
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
        PersonEntity personEntity = PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .build();

        if (person.hasPartner()) {
            Long partnerId = person.getPartner().getId();
            PersonEntity partner = getPerson(partnerId);
            personEntity.setPartner(partner);
        }

        if (person.hasParent1()) {
            Long parent1Id = person.getParent1().getId();
            PersonEntity parent1 = getPerson(parent1Id);
            personEntity.setParent1(parent1);
        }

        if (person.hasParent2()) {
            Long parent2Id = person.getParent2().getId();
            PersonEntity parent2 = getPerson(parent2Id);
            personEntity.setParent2(parent2);
        }

        return personEntity;
    }

    private PersonEntity getPerson(Long personId) {
        PersonEntity parent;

        Optional<PersonEntity> optionalPerson = personRepository.findById(personId);

        if (optionalPerson.isPresent()) {
            parent = optionalPerson.get();
        } else {
            parent = new PersonEntity(personId);
            personRepository.save(parent);
        }

        return parent;
    }
}
