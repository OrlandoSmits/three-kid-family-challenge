package nl.orlandosmits.threekidfamily.controller;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.dto.response.PostPeopleResponseDto;
import nl.orlandosmits.threekidfamily.mapper.PersonMapper;
import nl.orlandosmits.threekidfamily.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleRestV1Controller {

    private static final Logger log = LoggerFactory.getLogger(PeopleRestV1Controller.class);
    private final PeopleService peopleService;
    private final PersonMapper personMapper;

    public PeopleRestV1Controller(PeopleService peopleService, PersonMapper personMapper) {
        this.peopleService = peopleService;
        this.personMapper = personMapper;
    }

    @PostMapping
    public ResponseEntity<PostPeopleResponseDto> postPeople(@RequestBody PeopleRequestDto peopleRequestDto) {
        
        Person person = personMapper.mapFrom(peopleRequestDto);
        
        boolean validPerson = peopleService.isValidPerson(person);

        if (validPerson) {
            return ResponseEntity.ok(new PostPeopleResponseDto());
        }

        return ResponseEntity.status(444).build();
    }
}
