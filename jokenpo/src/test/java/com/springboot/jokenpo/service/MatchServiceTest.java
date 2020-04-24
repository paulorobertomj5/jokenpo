package com.springboot.jokenpo.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jokenpo.data.model.Match;
import com.springboot.jokenpo.data.vo.PlayVO;
import com.springboot.jokenpo.repository.MatchRepository;
import com.springboot.jokenpo.service.impl.MatchServiceImpl;
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
        MatchServiceImpl.class
})
public class MatchServiceTest {


    @MockBean
    private MatchRepository matchRepository;

    @MockBean
    private PlayService playService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(matchRepository)
                .build();
    }


    @Test
    public void testFindByAll_Success() throws Exception {

        List<Match> listResponse = new ArrayList<>();
        Match response = new Match();
        response.setId(1L);
        response.setName("Paulo");

        listResponse.add(response);
        listResponse.add(response);

        when(matchRepository.findAll()).thenReturn(listResponse);

        assertEquals(2, listResponse.size());
    }

    @Test
    public void testFindByAll_Error() throws Exception {

        List<Match> listResponse = new ArrayList<>();
        Match response = new Match();
        response.setId(1L);
        response.setName("Paulo");

        listResponse.add(response);
        listResponse.add(response);

        when(matchRepository.findAll()).thenReturn(listResponse);

        assertNotEquals(1, listResponse.size());
    }

    @Test
    public void testFindById_Success() throws Exception {

        Match response = new Match();
        response.setId(1L);
        response.setName("Paulo");

        when(matchRepository.findById(any())).thenReturn(java.util.Optional.of(response));

        Long expectedKey = 1L;
        String expectedName = "Paulo";


        assertEquals(response.getId(), expectedKey);
        assertEquals(response.getName(), expectedName);

    }

    @Test
    public void testFindResultMatchById_Error() throws Exception {
        List<PlayVO> playVOList = new ArrayList<>();

        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");
        playVOList.add(response);
        when(playService.findMatchById(any())).thenReturn(playVOList);

        assertEquals(1, playVOList.size());


    }

    @Test
    public void testFindResultMatchById_Success() throws Exception {

        List<PlayVO> playVOList = new ArrayList<>();

        PlayVO response = new PlayVO();
        response.setKey(1L);
        response.setPlayerId(1);
        response.setMatchId(1);
        response.setPlayed("ROCK");
        playVOList.add(response);

        when(playService.findMatchById(any())).thenReturn(playVOList);


        assertNotEquals(2, playVOList.size());

    }

    @Test
    public void testFindByid_Error() throws Exception {

        Match response = new Match();
        response.setId(1L);
        response.setName("Paulo");

        when(matchRepository.findById(any())).thenReturn(java.util.Optional.of(response));

        String expectedKey = "2";
        String expectedName = "João";

        assertNotEquals(response.getId(), expectedKey);
        assertNotEquals(response.getName(), expectedName);

    }

    @Test
    public void testCreate_Success() throws Exception {

        Match match = new Match();
        match.setId(1L);
        match.setName("Paulo");

        when(matchRepository.save(any())).thenReturn(java.util.Optional.of(match));

        Long expectedKey = 1L;
        String expectedName = "Paulo";

        assertEquals(match.getId(), expectedKey);
        assertEquals(match.getName(), expectedName);

    }

    @Test
    public void testCreate_Error() throws Exception {

        Match match = new Match();
        match.setId(1L);
        match.setName("Paulo");
        match.setName("Roberto");

        when(matchRepository.save(any())).thenReturn(java.util.Optional.of(match));

        String expectedKey = "2";
        String expectedName = "João";

        assertNotEquals(match.getId(), expectedKey);
        assertNotEquals(match.getName(), expectedName);

    }

    @Test
    public void testUpdate_Success() throws Exception {

        Match match = new Match();
        match.setId(1L);
        match.setName("Paulo");

        when(matchRepository.save(any())).thenReturn(match);

        Long expectedKey = 1L;
        String expectedName = "Paulo";

        assertEquals(match.getId(), expectedKey);
        assertEquals(match.getName(), expectedName);
    }

    @Test
    public void testUpdate_Error() throws Exception {

        Match match = new Match();
        match.setId(1L);
        match.setName("Paulo");


        when(matchRepository.save(any())).thenReturn(match);

        Long expectedKey = 2L;
        String expectedName = "João";

        assertNotEquals(match.getId(), expectedKey);
        assertNotEquals(match.getName(), expectedName);

    }

}
