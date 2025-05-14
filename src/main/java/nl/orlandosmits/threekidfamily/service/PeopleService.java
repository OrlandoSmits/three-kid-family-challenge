package nl.orlandosmits.threekidfamily.service;

import lombok.extern.slf4j.Slf4j;
import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import nl.orlandosmits.threekidfamily.mapper.PersonEntityMapper;
import nl.orlandosmits.threekidfamily.mapper.PersonMapper;
import nl.orlandosmits.threekidfamily.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PeopleService {

    private final PersonMapper personMapper;
    private final PersonEntityMapper personEntityMapper;
    private final PersonRepository personRepository;
    private final PersonValidator personValidator;

    public PeopleService(PersonMapper personMapper, PersonEntityMapper personEntityMapper,
            PersonRepository personRepository, PersonValidator personValidator) {
        this.personMapper = personMapper;
        this.personEntityMapper = personEntityMapper;
        this.personRepository = personRepository;
        this.personValidator = personValidator;
    }

    public boolean anyPersonIsValid() {
        return personRepository.findAll().stream()
                .map(personMapper::mapFrom)
                .filter(personValidator::hasPartner)
                .filter(personValidator::hasThreeChildrenWithPartnerAsParent)
                .anyMatch(personValidator::hasOneChildUnderEighteen);
    }


    public Person getPerson(PeopleRequestDto peopleRequestDto) {
        return personMapper.mapFrom(peopleRequestDto);
    }

    public void saveOrUpdate(Person person) {
        PersonEntity personEntity = personEntityMapper.mapFrom(person);
        personRepository.save(personEntity);
    }
}
