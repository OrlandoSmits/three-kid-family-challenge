package nl.orlandosmits.threekidfamily.mapper;

import org.springframework.stereotype.Component;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import nl.orlandosmits.threekidfamily.repository.PersonRepository;

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

		if (person.hasPartner()) {
			entity.setPartnerId(person.getPartner().getId());
		}

		if (person.hasParent1()) {
			entity.setParent1Id(person.getParent1().getId());
		}

		if (person.hasParent2()) {
			entity.setParent2Id(person.getParent2().getId());
		}

		return entity;
	}
}
