package com.example.demo.controller.impl;

import com.example.demo.controller.impl.CustomUtils;
import com.example.demo.controller.impl.RatingControllerImpl;
import com.example.demo.dto.RatingDTO;
import com.example.demo.mapper.RatingMapper;
import com.example.demo.mapper.ReferenceMapper;
import com.example.demo.model.Rating;
import com.example.demo.service.RatingService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RatingControllerImplTest {
    //TODO: create the data Test generator class RatingBuilder
    private static final String ENDPOINT_URL = "/ratings";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private RatingControllerImpl ratingController;
    @MockBean
    private RatingService ratingService;
    @MockBean
    private RatingMapper ratingMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.ratingController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(ratingMapper.asDTOList(ArgumentMatchers.any())).thenReturn(RatingBuilder.getListDTO());

        Mockito.when(ratingService.findAll()).thenReturn(RatingBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(ratingMapper.asDTO(ArgumentMatchers.any())).thenReturn(RatingBuilder.getDTO());

        Mockito.when(ratingService.findById(ArgumentMatchers.anyString())).thenReturn(java.util.Optional.of(RatingBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(ratingService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(ratingService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(ratingMapper.asEntity(ArgumentMatchers.any())).thenReturn(RatingBuilder.getEntity());
        Mockito.when(ratingService.save(ArgumentMatchers.any(Rating.class))).thenReturn(RatingBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(RatingBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(ratingService, Mockito.times(1)).save(ArgumentMatchers.any(Rating.class));
        Mockito.verifyNoMoreInteractions(ratingService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(ratingMapper.asEntity(ArgumentMatchers.any())).thenReturn(RatingBuilder.getEntity());
        Mockito.when(ratingService.update(ArgumentMatchers.any(), ArgumentMatchers.anyString())).thenReturn(RatingBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(RatingBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(ratingService, Mockito.times(1)).update(ArgumentMatchers.any(Rating.class), ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(ratingService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(ratingService).deleteById(ArgumentMatchers.anyString());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(ratingService, Mockito.times(1)).deleteById(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(ratingService);
    }
}