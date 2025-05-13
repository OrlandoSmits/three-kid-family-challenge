package nl.orlandosmits.threekidfamily.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PeopleRestController.class)
class PeopleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostPerson() throws Exception {

        mockMvc.perform(post("/api/v1/people")
                .contentType(APPLICATION_JSON)
                .content("""  
                      {
                        "id"        :  42,
                        "name"      :  "Ada Lovelace",
                        "birthDate" : "1815-12-10",
                        "parent1"   :  {  "id": 1 },
                        "parent2"   :  {  "id": 2 },
                        "partner"   :  {  "id":  99 },
                        "children"  :  [{ "id":  77}, { "id": 78}]
                     }"""))
                .andExpect(status().isOk());

    }
}