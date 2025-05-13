package nl.orlandosmits.threekidfamily.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nl.orlandosmits.threekidfamily.service.PeopleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PeopleRestV1Controller.class)
class PeopleRestV1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeopleService peopleService;

    @Test
    void postPerson_doesNotSatisfyThePattern() throws Exception {
        Mockito.when(peopleService.isValidPerson()).thenReturn(false);

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
                .andExpect(status().is(444));

    }

    @Test
    void postPerson_doesSatisfyThePattern() throws Exception {
        Mockito.when(peopleService.isValidPerson()).thenReturn(true);

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
                .andExpect(status().is(444));

    }
}