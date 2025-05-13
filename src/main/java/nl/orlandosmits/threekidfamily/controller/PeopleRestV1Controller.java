package nl.orlandosmits.threekidfamily.controller;

import nl.orlandosmits.threekidfamily.dto.PostPeopleResponse;
import nl.orlandosmits.threekidfamily.service.PeopleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleRestV1Controller {

    private final PeopleService peopleService;

    public PeopleRestV1Controller(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping
    public ResponseEntity<PostPeopleResponse> postPeople() {
        boolean validPerson = peopleService.isValidPerson();

        if (validPerson) {
            return ResponseEntity.ok(new PostPeopleResponse());
        }

        return ResponseEntity.status(444).build();
    }
}
