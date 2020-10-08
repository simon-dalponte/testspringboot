package com.example.demo.controller.impl;

import com.example.demo.controller.impl.CustomUtils;
import com.example.demo.controller.impl.GameControllerImpl;
import com.example.demo.dto.GameDTO;
import com.example.demo.mapper.GameMapper;
import com.example.demo.mapper.ReferenceMapper;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;
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
public class GameControllerImplTest {
    //TODO: create the data Test generator class GameBuilder
    private static final String ENDPOINT_URL = "/games";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private GameControllerImpl gameController;
    @MockBean
    private GameService gameService;
    @MockBean
    private GameMapper gameMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.gameController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(gameMapper.asDTOList(ArgumentMatchers.any())).thenReturn(GameBuilder.getListDTO());

        Mockito.when(gameService.findAll()).thenReturn(GameBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(gameMapper.asDTO(ArgumentMatchers.any())).thenReturn(GameBuilder.getDTO());

        Mockito.when(gameService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(GameBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(gameService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(gameService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(gameMapper.asEntity(ArgumentMatchers.any())).thenReturn(GameBuilder.getEntity());
        Mockito.when(gameService.save(ArgumentMatchers.any(Game.class))).thenReturn(GameBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(GameBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(gameService, Mockito.times(1)).save(ArgumentMatchers.any(Game.class));
        Mockito.verifyNoMoreInteractions(gameService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(gameMapper.asEntity(ArgumentMatchers.any())).thenReturn(GameBuilder.getEntity());
        Mockito.when(gameService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(GameBuilder.getEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CustomUtils.asJsonString(GameBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(gameService, Mockito.times(1)).update(ArgumentMatchers.any(Game.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(gameService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(gameService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(gameService, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(gameService);
    }
}