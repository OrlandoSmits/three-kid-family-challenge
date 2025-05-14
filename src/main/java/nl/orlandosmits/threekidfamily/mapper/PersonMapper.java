package nl.orlandosmits.threekidfamily.mapper;

import org.springframework.stereotype.Component;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;

@Component
public class PersonMapper {

    private final ParentMapper parentMapper;

    private final PartnerMapper partnerMapper;

    public PersonMapper(ParentMapper parentMapper, PartnerMapper partnerMapper) {
        this.parentMapper = parentMapper;
        this.partnerMapper = partnerMapper;
	}

    public Person mapFrom(PeopleRequestDto peopleRequestDto) {
        return Person.builder()
                .id(peopleRequestDto.id())
                .name(peopleRequestDto.name())
                .parent1(parentMapper.mapParent1From(peopleRequestDto).orElse(null))
                .parent2(parentMapper.mapParent2From(peopleRequestDto).orElse(null))
                .partner(partnerMapper.mapFrom(peopleRequestDto))
                .build();
    }

    public Person mapFrom(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .partner(partnerMapper.mapFrom(personEntity))
                .parent1(parentMapper.mapParent1From(personEntity))
                .parent1(parentMapper.mapParent2From(personEntity))
                .build();
    }

}
