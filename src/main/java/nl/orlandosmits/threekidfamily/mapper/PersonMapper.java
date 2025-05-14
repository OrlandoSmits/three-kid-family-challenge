package nl.orlandosmits.threekidfamily.mapper;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final ParentMapper parentMapper;

    private final PartnerMapper partnerMapper;
    private final ChildMapper childMapper;

    public PersonMapper(ParentMapper parentMapper, PartnerMapper partnerMapper, ChildMapper childMapper) {
        this.parentMapper = parentMapper;
        this.partnerMapper = partnerMapper;
        this.childMapper = childMapper;
    }

    public Person mapFrom(PeopleRequestDto peopleRequestDto) {
        return Person.builder()
                .id(peopleRequestDto.id())
                .name(peopleRequestDto.name())
                .birthDate(peopleRequestDto.birthDate())
                .parent1(parentMapper.mapParent1From(peopleRequestDto).orElse(null))
                .parent2(parentMapper.mapParent2From(peopleRequestDto).orElse(null))
                .partner(partnerMapper.mapFrom(peopleRequestDto))
                .children(childMapper.mapAllFrom(peopleRequestDto))
                .build();
    }

    public Person mapFrom(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .birthDate(personEntity.getBirthDate())
                .partner(partnerMapper.mapFrom(personEntity))
                .parent1(parentMapper.mapParent1From(personEntity))
                .parent1(parentMapper.mapParent2From(personEntity))
                .children(childMapper.mapAllFrom(personEntity))
                .build();
    }

}
