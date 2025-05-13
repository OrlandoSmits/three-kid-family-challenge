package nl.orlandosmits.threekidfamily.mapper;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person mapFrom(PeopleRequestDto peopleRequestDto) {
        return Person.builder()
                .name(peopleRequestDto.name())
                .build();
    }

}
