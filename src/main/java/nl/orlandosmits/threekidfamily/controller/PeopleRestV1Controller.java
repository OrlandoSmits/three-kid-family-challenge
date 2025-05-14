package nl.orlandosmits.threekidfamily.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.dto.response.PostPeopleResponseDto;
import nl.orlandosmits.threekidfamily.service.PeopleService;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleRestV1Controller {

    private final PeopleService peopleService;

    public PeopleRestV1Controller(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping
    public ResponseEntity<PostPeopleResponseDto> postPeople(@RequestBody PeopleRequestDto peopleRequestDto) {
        Person person = peopleService.getPerson(peopleRequestDto);
        
        peopleService.saveOrUpdate(person);
        boolean validPerson = peopleService.anyPersonIsValid();

        if (validPerson) {
            return ResponseEntity.ok(new PostPeopleResponseDto());
        }

        return ResponseEntity.status(444).build();
    }
}
