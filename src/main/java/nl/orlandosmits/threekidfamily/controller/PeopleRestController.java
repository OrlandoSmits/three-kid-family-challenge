package nl.orlandosmits.threekidfamily.controller;

import nl.orlandosmits.threekidfamily.dto.PostPeopleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleRestController {

    @PostMapping
    public ResponseEntity<PostPeopleResponse> postPeople() {
        return ResponseEntity.ok(new PostPeopleResponse());
    }
}
