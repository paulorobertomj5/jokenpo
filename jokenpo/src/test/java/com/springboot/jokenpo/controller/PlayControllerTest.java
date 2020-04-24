package com.springboot.jokenpo.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jokenpo.data.vo.PlayVO;
import com.springboot.jokenpo.service.PlayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        PlayController.class
})
public class PlayControllerTest {

    @Autowired
    private PlayController controller;

    @MockBean
    private PlayService playService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }


    @Test
    public void testFindByAll_Success() throws Exception {

        List<PlayVO> listResponse = new ArrayList<>();
        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setMatchId(1);
        response.setPlayerId(1);
        response.setPlayed("ROCK");

        listResponse.add(response);

        when(playService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/play/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedMatchId = "1";
        String expectedPlayedId = "1";
        String expectedPlayed = "ROCK";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedMatchId));
        assertTrue(jsonResult.contains(expectedPlayedId));
        assertTrue(jsonResult.contains(expectedPlayed));
    }

    @Test
    public void testFindByAll_Error() throws Exception {

        List<PlayVO> listResponse = new ArrayList<>();
        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setMatchId(1);
        response.setPlayerId(1);
        response.setPlayed("ROCK");

        listResponse.add(response);

        when(playService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/play/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedMatchId = "2";
        String expectedPlayedId = "2";
        String expectedPlayed = "PAPER";



        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedMatchId));
        assertFalse(jsonResult.contains(expectedPlayedId));
        assertFalse(jsonResult.contains(expectedPlayed));
    }

    @Test
    public void testFindById_Success() throws Exception {

        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setMatchId(1);
        response.setPlayerId(1);
        response.setPlayed("ROCK");


        when(playService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/play/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedMatchId = "1";
        String expectedPlayedId = "1";
        String expectedPlayed = "ROCK";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedMatchId));
        assertTrue(jsonResult.contains(expectedPlayedId));
        assertTrue(jsonResult.contains(expectedPlayed));
    }

    @Test
    public void testFindByid_Error() throws Exception {

        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setMatchId(1);
        response.setPlayerId(1);
        response.setPlayed("ROCK");


        when(playService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/play/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedMatchId = "2";
        String expectedPlayedId = "2";
        String expectedPlayed = "PAPER";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedMatchId));
        assertFalse(jsonResult.contains(expectedPlayedId));
        assertFalse(jsonResult.contains(expectedPlayed));
    }

    @Test
    public void testCreate_Success() throws Exception {

        PlayVO playVO = new PlayVO();
        playVO.setKey(1L);
        playVO.setMatchId(1);
        playVO.setPlayerId(1);
        playVO.setPlayed("ROCK");


        when(playService.create(any())).thenReturn(playVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/play/")
                .content(objectMapper.writeValueAsString(playVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedMatchId = "1";
        String expectedPlayedId = "1";
        String expectedPlayed = "ROCK";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedMatchId));
        assertTrue(jsonResult.contains(expectedPlayedId));
        assertTrue(jsonResult.contains(expectedPlayed));
    }

    @Test
    public void testCreate_Error() throws Exception {

        PlayVO playVO = new PlayVO();
        playVO.setKey(1L);
        playVO.setMatchId(1);
        playVO.setPlayerId(1);
        playVO.setPlayed("ROCK");


        when(playService.create(any())).thenReturn(playVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/play/")
                .content(objectMapper.writeValueAsString(playVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedMatchId = "2";
        String expectedPlayedId = "2";
        String expectedPlayed = "PAPER";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedMatchId));
        assertFalse(jsonResult.contains(expectedPlayedId));
        assertFalse(jsonResult.contains(expectedPlayed));
    }

    @Test
    public void testUpdate_Success() throws Exception {

        PlayVO playVO = new PlayVO();
        playVO.setKey(1L);
        playVO.setMatchId(1);
        playVO.setPlayerId(1);
        playVO.setPlayed("ROCK");


        when(playService.update(any())).thenReturn(playVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/play/")
                .content(objectMapper.writeValueAsString(playVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedMatchId = "1";
        String expectedPlayedId = "1";
        String expectedPlayed = "ROCK";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();

        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedMatchId));
        assertTrue(jsonResult.contains(expectedPlayedId));
        assertTrue(jsonResult.contains(expectedPlayed));
    }

    @Test
    public void testUpdate_Error() throws Exception {

        PlayVO playVO = new PlayVO();
        playVO.setKey(1L);
        playVO.setMatchId(1);
        playVO.setPlayerId(1);
        playVO.setPlayed("ROCK");


        when(playService.update(any())).thenReturn(playVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/play/")
                .content(objectMapper.writeValueAsString(playVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedMatchId = "2";
        String expectedPlayedId = "2";
        String expectedPlayed = "PAPER";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedMatchId));
        assertFalse(jsonResult.contains(expectedPlayedId));
        assertFalse(jsonResult.contains(expectedPlayed));
    }

}
