package nl.orlandosmits.threekidfamily.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import nl.orlandosmits.threekidfamily.domain.Child;
import nl.orlandosmits.threekidfamily.dto.ChildDto;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import nl.orlandosmits.threekidfamily.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class ChildMapper {

    private final PersonRepository personRepository;

    public ChildMapper(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private Child mapFrom(ChildDto childDto) {
        return Child.builder()
                .id(childDto.id())
                .parent1Id(childDto.id())
                .build();
    }

    public List<Child> mapAllFrom(PeopleRequestDto peopleRequestDto) {
        if (peopleRequestDto.children().isEmpty()) {
            return new ArrayList<>();
        }

        return peopleRequestDto.children().stream()
                .map(this::mapFrom)
                .toList();
    }

    private Child mapFrom(PersonEntity personEntity) {
        return Child.builder()
                .id(personEntity.getId())
                .birthDate(personEntity.getBirthDate())
                .parent1Id(personEntity.getParent1Id())
                .parent2Id(personEntity.getParent2Id())
                .build();
    }


    public List<Child> mapAllFrom(PersonEntity personEntity) {
        if (Objects.isNull(personEntity.getChildrenIds())) {
            return new ArrayList<>();
        }

        return personEntity.getChildrenIds()
                .stream()
                .map(personRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::mapFrom)
                .toList();
    }

}
