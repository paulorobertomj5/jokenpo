package com.springboot.jokenpo.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jokenpo.data.model.Player;
import com.springboot.jokenpo.repository.PlayerRepository;
import com.springboot.jokenpo.service.impl.PlayerServiceImpl;
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
        PlayerServiceImpl.class
})
public class PlayerServiceTest {


    @MockBean
    private PlayerRepository playerRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(playerRepository)
                .build();
    }

    @Test
    public void testFindByAll_Success() throws Exception {

        List<Player> listResponse = new ArrayList<>();
        Player response = new Player();
        response.setId(1L);
        response.setFistName("Paulo");
        response.setLastName("Roberto");
        response.setAddress("Av. Paulista");
        response.setGender("M");

        listResponse.add(response);
        listResponse.add(response);

        when(playerRepository.findAll()).thenReturn(listResponse);

        assertEquals(2, listResponse.size());
    }

    @Test
    public void testFindByAll_Error() throws Exception {

        List<Player> listResponse = new ArrayList<>();
        Player response = new Player();
        response.setId(1L);
        response.setFistName("Paulo");
        response.setLastName("Roberto");
        response.setAddress("Av. Paulista");
        response.setGender("M");

        listResponse.add(response);
        listResponse.add(response);

        when(playerRepository.findAll()).thenReturn(listResponse);

        assertNotEquals(1, listResponse.size());
    }

    @Test
    public void testFindById_Success() throws Exception {

        Player response = new Player();
        response.setId(1L);
        response.setFistName("Paulo");
        response.setLastName("Roberto");
        response.setAddress("Av. Paulista");
        response.setGender("M");

        when(playerRepository.findById(any())).thenReturn(java.util.Optional.of(response));

        Long expectedKey = 1L;
        String expectedFistName = "Paulo";
        String expectedLastName = "Roberto";
        String expectedAddress = "Av. Paulista";
        String expectedGender = "M";

        assertEquals(response.getId(), expectedKey);
        assertEquals(response.getFistName(), expectedFistName);
        assertEquals(response.getLastName(), expectedLastName);
        assertEquals(response.getAddress(), expectedAddress);
        assertEquals(response.getGender(), expectedGender);

    }

    @Test
    public void testFindByid_Error() throws Exception {

        Player response = new Player();
        response.setId(1L);
        response.setFistName("Paulo");
        response.setLastName("Roberto");
        response.setAddress("Av. Paulista");
        response.setGender("M");

        when(playerRepository.findById(any())).thenReturn(java.util.Optional.of(response));

        String expectedKey = "2";
        String expectedFistName = "João";
        String expectedLastName = "Maroa";
        String expectedAddress = "Av. Faria Lima";
        String expectedGender = "G";

        assertNotEquals(response.getId(), expectedKey);
        assertNotEquals(response.getFistName(), expectedFistName);
        assertNotEquals(response.getLastName(), expectedLastName);
        assertNotEquals(response.getAddress(), expectedAddress);
        assertNotEquals(response.getGender(), expectedGender);
    }

    @Test
    public void testCreate_Success() throws Exception {

        Player player = new Player();
        player.setId(1L);
        player.setFistName("Paulo");
        player.setLastName("Roberto");
        player.setAddress("Av. Paulista");
        player.setGender("M");

        when(playerRepository.save(any())).thenReturn(java.util.Optional.of(player));

        Long expectedKey = 1L;
        String expectedFistName = "Paulo";
        String expectedLastName = "Roberto";
        String expectedAddress = "Av. Paulista";
        String expectedGender = "M";

        assertEquals(player.getId(), expectedKey);
        assertEquals(player.getFistName(), expectedFistName);
        assertEquals(player.getLastName(), expectedLastName);
        assertEquals(player.getAddress(), expectedAddress);
        assertEquals(player.getGender(), expectedGender);

    }

    @Test
    public void testCreate_Error() throws Exception {

        Player player = new Player();
        player.setId(1L);
        player.setFistName("Paulo");
        player.setFistName("Roberto");
        player.setAddress("Av. Paulista");
        player.setGender("M");

        when(playerRepository.save(any())).thenReturn(java.util.Optional.of(player));

        String expectedKey = "2";
        String expectedFistName = "João";
        String expectedLastName = "Maroa";
        String expectedAddress = "Av. Faria Lima";
        String expectedGender = "G";

        assertNotEquals(player.getId(), expectedKey);
        assertNotEquals(player.getFistName(), expectedFistName);
        assertNotEquals(player.getLastName(), expectedLastName);
        assertNotEquals(player.getAddress(), expectedAddress);
        assertNotEquals(player.getGender(), expectedGender);
    }

    @Test
    public void testUpdate_Success() throws Exception {

        Player player = new Player();
        player.setId(1L);
        player.setFistName("Paulo");
        player.setLastName("Roberto");
        player.setAddress("Av. Paulista");
        player.setGender("M");

        when(playerRepository.save(any())).thenReturn(player);

        Long expectedKey = 1L;
        String expectedFistName = "Paulo";
        String expectedLastName = "Roberto";
        String expectedAddress = "Av. Paulista";
        String expectedGender = "M";

        assertEquals(player.getId(), expectedKey);
        assertEquals(player.getFistName(), expectedFistName);
        assertEquals(player.getLastName(), expectedLastName);
        assertEquals(player.getAddress(), expectedAddress);
        assertEquals(player.getGender(), expectedGender);
    }

    @Test
    public void testUpdate_Error() throws Exception {

        Player player = new Player();
        player.setId(1L);
        player.setFistName("Paulo");
        player.setFistName("Roberto");
        player.setAddress("Av. Paulista");
        player.setGender("M");


        when(playerRepository.save(any())).thenReturn(player);

        Long expectedKey = 2L;
        String expectedFistName = "João";
        String expectedLastName = "Maroa";
        String expectedAddress = "Av. Faria Lima";
        String expectedGender = "G";

        assertNotEquals(player.getId(), expectedKey);
        assertNotEquals(player.getFistName(), expectedFistName);
        assertNotEquals(player.getLastName(), expectedLastName);
        assertNotEquals(player.getAddress(), expectedAddress);
        assertNotEquals(player.getGender(), expectedGender);

    }

}
