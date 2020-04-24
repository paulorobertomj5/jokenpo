package com.springboot.jokenpo.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jokenpo.data.model.Play;
import com.springboot.jokenpo.data.vo.PlayVO;
import com.springboot.jokenpo.repository.PlayCustomRepository;
import com.springboot.jokenpo.repository.PlayRepository;
import com.springboot.jokenpo.service.impl.PlayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        PlayServiceImpl.class
})
public class PlayServiceTest {


    @MockBean
    private PlayRepository playRepository;
    @MockBean
    private PlayCustomRepository playCustomRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(playRepository)
                .build();
    }

    @Test
    public void testFindByAll_Success() throws Exception {

        List<Play> listResponse = new ArrayList<>();
        Play response = new Play();
        response.setId(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");

        listResponse.add(response);
        listResponse.add(response);

        when(playRepository.findAll()).thenReturn(listResponse);

        assertEquals(2, listResponse.size());
    }

    @Test
    public void testFindByAll_Error() throws Exception {

        List<Play> listResponse = new ArrayList<>();
        Play response = new Play();
        response.setId(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");

        listResponse.add(response);
        listResponse.add(response);

        when(playRepository.findAll()).thenReturn(listResponse);

        assertNotEquals(1, listResponse.size());
    }

    @Test
    public void testFindById_Success() throws Exception {

        Play response = new Play();
        response.setId(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");

        when(playRepository.findById(any())).thenReturn(java.util.Optional.of(response));

        Long expectedKey = 1L;
        Integer expectedPlayerId = 1;
        Integer expectedMatchId = 1;
        String expectedPlayed = "ROCK";


        assertEquals(response.getId(), expectedKey);
        assertEquals(response.getPlayerId(), expectedPlayerId);
        assertEquals(response.getMatchId(), expectedMatchId);
        assertEquals(response.getPlayed(), expectedPlayed);

    }

    @Test
    public void testFindByid_Error() throws Exception {

        Play response = new Play();
        response.setId(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");

        when(playRepository.findById(any())).thenReturn(java.util.Optional.of(response));

        Long expectedKey = 2L;
        Integer expectedPlayerId = 2;
        Integer expectedMatchId = 2;
        String expectedPlayed = "PAPER";

        assertNotEquals(response.getId(), expectedKey);
        assertNotEquals(response.getPlayerId(), expectedPlayerId);
        assertNotEquals(response.getMatchId(), expectedMatchId);
        assertNotEquals(response.getPlayed(), expectedPlayed);
    }

    @Test
    public void testFindMatchById_Success() throws Exception {

        List<PlayVO> playVOList =  new ArrayList<>();
        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");
        playVOList.add(response);
        when(playCustomRepository.findMatchById(any())).thenReturn(playVOList);

        Long expectedKey = 1L;
        Integer expectedPlayerId = 1;
        Integer expectedMatchId = 1;
        String expectedPlayed = "ROCK";


        assertEquals(1, playVOList.size());


    }

    @Test
    public void testFindMatchById_Error() throws Exception {

        List<PlayVO> playVOList =  new ArrayList<>();
        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");
        playVOList.add(response);
        when(playCustomRepository.findMatchById(any())).thenReturn(playVOList);

        Long expectedKey = 1L;
        Integer expectedPlayerId = 1;
        Integer expectedMatchId = 1;
        String expectedPlayed = "ROCK";


        assertNotEquals(2, playVOList.size());
    }

    @Test
    public void testCreate_Success() throws Exception {

        Play play = new Play();
        play.setId(1L);
        play.setPlayerId(1);
        play.setMatchId(1);
        play.setPlayed("ROCK");

        when(playRepository.save(any())).thenReturn(java.util.Optional.of(play));

        Long expectedKey = 1L;
        Integer expectedPlayerId = 1;
        Integer expectedMatchId = 1;
        String expectedPlayed = "ROCK";

        assertEquals(play.getId(), expectedKey);
        assertEquals(play.getPlayerId(), expectedPlayerId);
        assertEquals(play.getMatchId(), expectedMatchId);
        assertEquals(play.getPlayed(), expectedPlayed);

    }

    @Test
    public void testCreate_Error() throws Exception {

        Play play = new Play();
        play.setId(1L);
        play.setPlayerId(1);
        play.setMatchId(1);
        play.setPlayed("ROCK");

        when(playRepository.save(any())).thenReturn(java.util.Optional.of(play));

        Long expectedKey = 2L;
        Integer expectedPlayerId = 2;
        Integer expectedMatchId = 2;
        String expectedPlayed = "PAPER";

        assertNotEquals(play.getId(), expectedKey);
        assertNotEquals(play.getPlayerId(), expectedPlayerId);
        assertNotEquals(play.getMatchId(), expectedMatchId);
        assertNotEquals(play.getPlayed(), expectedPlayed);
    }

    @Test
    public void testUpdate_Success() throws Exception {

        Play play = new Play();
        play.setId(1L);
        play.setPlayerId(1);
        play.setMatchId(1);
        play.setPlayed("ROCK");

        when(playRepository.save(any())).thenReturn(play);

        Long expectedKey = 1L;
        Integer expectedPlayerId = 1;
        Integer expectedMatchId = 1;
        String expectedPlayed = "ROCK";

        assertEquals(play.getId(), expectedKey);
        assertEquals(play.getPlayerId(), expectedPlayerId);
        assertEquals(play.getMatchId(), expectedMatchId);
        assertEquals(play.getPlayed(), expectedPlayed);
    }

    @Test
    public void testUpdate_Error() throws Exception {

        Play play = new Play();
        play.setId(1L);
        play.setPlayerId(1);
        play.setMatchId(1);
        play.setPlayed("ROCK");

        when(playRepository.save(any())).thenReturn(play);

        Long expectedKey = 1L;
        Integer expectedPlayerId = 1;
        Integer expectedMatchId = 1;
        String expectedPlayed = "ROCK";

        assertEquals(play.getId(), expectedKey);
        assertEquals(play.getPlayerId(), expectedPlayerId);
        assertEquals(play.getMatchId(), expectedMatchId);
        assertEquals(play.getPlayed(), expectedPlayed);

    }

}
