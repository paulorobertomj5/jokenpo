package com.springboot.jokenpo.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jokenpo.data.vo.MatchVO;
import com.springboot.jokenpo.service.MatchService;
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
        MatchController.class
})
public class MatchControllerTest {

    @Autowired
    private MatchController controller;

    @MockBean
    private MatchService matchService;

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

        List<MatchVO> listResponse = new ArrayList<>();
        MatchVO response = new MatchVO();
        response.setKey(1L);
        response.setName("Teste");

        listResponse.add(response);

        when(matchService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/match/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedName = "Teste";

        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedName));

    }

    @Test
    public void testFindByAll_Error() throws Exception {

        List<MatchVO> listResponse = new ArrayList<>();
        MatchVO response = new MatchVO();
        response.setKey(1L);
        response.setName("Teste");


        listResponse.add(response);

        when(matchService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/match/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedName = "Teste 2";

        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedName));

    }

    @Test
    public void testFindById_Success() throws Exception {

        MatchVO response = new MatchVO();
        response.setKey(1L);
        response.setName("Teste");


        when(matchService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/match/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedName = "Teste";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedName));

    }

    @Test
    public void testFindByid_Error() throws Exception {

        MatchVO response = new MatchVO();
        response.setKey(1L);
        response.setName("Teste");

        when(matchService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/match/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedName = "Teste";



        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedName));

    }

    @Test
    public void testCreate_Success() throws Exception {

        MatchVO matchVO = new MatchVO();
        matchVO.setKey(1L);
        matchVO.setName("Teste");


        when(matchService.create(any())).thenReturn(matchVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/match/")
                .content(objectMapper.writeValueAsString(matchVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedName = "Teste";

        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedName));


    }

    @Test
    public void testCreate_Error() throws Exception {

        MatchVO matchVO = new MatchVO();
        matchVO.setKey(1L);
        matchVO.setName("Teste");

        when(matchService.create(any())).thenReturn(matchVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/match/")
                .content(objectMapper.writeValueAsString(matchVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedName = "Teste 2";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedName));

    }

    @Test
    public void testUpdate_Success() throws Exception {

        MatchVO matchVO = new MatchVO();
        matchVO.setKey(1L);
        matchVO.setName("Teste");

        when(matchService.update(any())).thenReturn(matchVO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/match/")
                .content(objectMapper.writeValueAsString(matchVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedName = "Teste";

        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedName));

    }

    @Test
    public void testUpdate_Error() throws Exception {

        MatchVO matchVO = new MatchVO();
        matchVO.setKey(1L);
        matchVO.setName("Teste");

        when(matchService.update(any())).thenReturn(matchVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/match/")
                .content(objectMapper.writeValueAsString(matchVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedName = "Teste 2";

        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();

        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedName));

    }

}
