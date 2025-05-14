package nl.orlandosmits.threekidfamily.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PeopleRestV1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    void postPerson_doesNotSatisfyThePattern() throws Exception {
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
    @DirtiesContext
    void postPerson_doesSatisfyThePattern() throws Exception {
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
                                }"""))
                .andExpect(status().is(444));

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
                                }"""))
                .andExpect(status().is(444));

        mockMvc.perform(post("/api/v1/people")
                        .contentType(APPLICATION_JSON)
                        .content("""  
                                 {
                                   "id"        :  77,
                                   "name"      :  "Chad Lovelace",
                                   "birthDate" : "2020-12-10",
                                   "parent1"   :  {  "id": 42 },
                                   "parent2"   :  {  "id": 99 },
                                   "partner"   :  {  "id":  1 },
                                   "children"  :  [{ "id":  42}]
                                }"""))
                .andExpect(status().is(444));

        mockMvc.perform(post("/api/v1/people")
                        .contentType(APPLICATION_JSON)
                        .content("""  
                                 {
                                   "id"        :  78,
                                   "name"      :  "Brad Lovelace",
                                   "birthDate" : "2000-12-10",
                                   "parent1"   :  {  "id": 42 },
                                   "parent2"   :  {  "id": 99 },
                                   "partner"   :  {  "id":  1 },
                                   "children"  :  [{ "id":  42}]
                                }"""))
                .andExpect(status().is(444));

        mockMvc.perform(post("/api/v1/people")
                        .contentType(APPLICATION_JSON)
                        .content("""  
                                 {
                                   "id"        :  79,
                                   "name"      :  "Peter Lovelace",
                                   "birthDate" : "2000-12-10",
                                   "parent1"   :  {  "id": 42 },
                                   "parent2"   :  {  "id": 99 },
                                   "partner"   :  {  "id":  1 },
                                   "children"  :  [{ "id":  42}]
                                }"""))
                .andExpect(status().is(444));

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
                                }"""))
                .andExpect(status().isOk());

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