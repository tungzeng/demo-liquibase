package com.tungzeng.liquibasedemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tungzeng.liquibasedemo.controller.Controller;
import com.tungzeng.liquibasedemo.model.Person;
import com.tungzeng.liquibasedemo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class PersonApiControllerTests {

    private static final String END_POINT_PATH = "/person";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void testAddSouldReturn400BadRequest() throws Exception {

        Person person = new Person("", "", "");

        String request = objectMapper.writeValueAsString(person);

        mockMvc.perform(post(END_POINT_PATH + "/add").contentType("application/json")
                .content(request))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    public void testAddSouldReturn201RequestCreated() throws Exception {

        Person person = new Person("Pham Tung Giang", "170", "Ha Noi");

        String request = objectMapper.writeValueAsString(person);

        mockMvc.perform(post(END_POINT_PATH + "/add").contentType("application/json")
                .content(request))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void testGetByIdSouldReturn404NotFound() throws Exception {

        int id = 2;

        mockMvc.perform(get(END_POINT_PATH + "/person/" + id))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void testGetByIdShouldReturn200RequestOk() throws Exception {

        int id = 1;

        Person person = new Person("Tung Giang", "170", "Ha Noi");

        Mockito.when(personRepository.findById(id)).thenReturn(java.util.Optional.of(person));
        String request = objectMapper.writeValueAsString(person);

        mockMvc.perform(get(END_POINT_PATH + "/person/" + id).contentType("application/json").content(request))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testGetListAllPersonShouldReturn204NoContent() throws Exception {

        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().isNoContent())
                .andDo(print());

    }

    @Test
    public void testGetListAllPersonShouldReturn200RequestOk() throws Exception {

        Person person1 = new Person(1,"Giang", "170", "Ha Noi");
        Person person2 = new Person(2,"Trang", "170", "Ha Noi");

        List<Person> list = List.of(person1, person2);

        Mockito.when(personRepository.findAll()).thenReturn(list);

        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());

    }

    @Test
    public void testDeleteShouldReturn404NotFound() throws Exception {

        int id = 2;

        Mockito.doThrow(EmptyResultDataAccessException.class).when(personRepository).deleteById(id);

        mockMvc.perform(delete(END_POINT_PATH + "/delete/" + id))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void testDeleteShouldReturn200RequestOk() throws Exception {

        int id = 1;

        Mockito.doNothing().when(personRepository).deleteById(id);

        mockMvc.perform(delete(END_POINT_PATH + "/delete/" + id))
                .andExpect(status().isOk())
                .andDo(print());

    }

}
