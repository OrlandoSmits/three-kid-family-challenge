package nl.orlandosmits.threekidfamily.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import nl.orlandosmits.threekidfamily.domain.Person;
import nl.orlandosmits.threekidfamily.dto.request.PeopleRequestDto;
import nl.orlandosmits.threekidfamily.service.PeopleService;

@WebMvcTest(PeopleRestV1Controller.class)
class PeopleRestV1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeopleService peopleService;

    @Test
    void postPerson_doesNotSatisfyThePattern() throws Exception {
        Person person = mock(Person.class);

        when(peopleService.getPerson(any(PeopleRequestDto.class))).thenReturn(person);
        when(peopleService.anyPersonIsValid()).thenReturn(false);

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
        Person person = mock(Person.class);

        when(peopleService.getPerson(any(PeopleRequestDto.class))).thenReturn(person);
        when(peopleService.anyPersonIsValid()).thenReturn(true);

        mockMvc.perform(post("/api/v1/people")
                .contentType(APPLICATION_JSON)
                .content("""  
                         {
                           "id"        :  1,
                           "name"      :  "Pada Lovelace",
                           "birthDate" : "1799-12-10",
                           "parent1"   :  { },
                           "parent2"   :  { },
                           "partner"   :  {  "id":  2 },
                           "children"  :  [{ "id":  42}]
                        }"""));

        mockMvc.perform(post("/api/v1/people")
                .contentType(APPLICATION_JSON)
                .content("""  
                         {
                           "id"        :  2,
                           "name"      :  "Mada Lovelace",
                           "birthDate" : "1799-12-10",
                           "parent1"   :  { },
                           "parent2"   :  { },
                           "partner"   :  {  "id":  1 },
                           "children"  :  [{ "id":  42}]
                        }"""));

        mockMvc.perform(post("/api/v1/people")
                .contentType(APPLICATION_JSON)
                .content("""  
                         {
                           "id"        :  77,
                           "name"      :  "Chad Lovelace",
                           "birthDate" : "1840-12-10",
                           "parent1"   :  {  "id": 42 },
                           "parent2"   :  {  "id": 99 },
                           "partner"   :  {  "id":  1 },
                           "children"  :  [{ "id":  42}]
                        }"""));

        mockMvc.perform(post("/api/v1/people")
                .contentType(APPLICATION_JSON)
                .content("""  
                         {
                           "id"        :  78,
                           "name"      :  "Brad Lovelace",
                           "birthDate" : "1842-12-10",
                           "parent1"   :  {  "id": 42 },
                           "parent2"   :  {  "id": 99 },
                           "partner"   :  {  "id":  1 },
                           "children"  :  [{ "id":  42}]
                        }"""));

        mockMvc.perform(post("/api/v1/people")
                .contentType(APPLICATION_JSON)
                .content("""  
                         {
                           "id"        :  79,
                           "name"      :  "Peter Lovelace",
                           "birthDate" : "1844-12-10",
                           "parent1"   :  {  "id": 42 },
                           "parent2"   :  {  "id": 99 },
                           "partner"   :  {  "id":  1 },
                           "children"  :  [{ "id":  42}]
                        }"""));

        mockMvc.perform(post("/api/v1/people")
                .contentType(APPLICATION_JSON)
                .content("""  
                         {
                           "id"        :  99,
                           "name"      :  "John Lovelace",
                           "birthDate" : "1815-12-10",
                           "parent1"   :  { },
                           "parent2"   :  { },
                           "partner"   :  {  "id":  42 },
                           "children"  :  [{ "id":  77}, { "id": 78}, { "id": 79}]
                        }"""));


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
                                   "children"  :  [{ "id":  77}, { "id": 78}, { "id": 79}]
                                }"""))
                .andExpect(status().isOk());

    }
}