package com.example.MyFirstProjectPostgreSQL.conroller.footballTeam;

import com.example.MyFirstProjectPostgreSQL.controller.FootballTeamController;
import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.service.footballTeam.FootballTeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FootballTeamControllerTest {
    FootballTeamService footballTeamService = Mockito.mock(FootballTeamService.class);
    FootballTeamController footballTeamController = new FootballTeamController(footballTeamService);

    private static final Long ID = 111L;
    private Pageable pageable;
    private static final String TEAMNAME = "FC Barcelona";
    private static final int YEAROFFOUNDATION = 1899;
    private static final long TEAMBUDGET = 99999999;
    private FootballTeamDTO footballTeamDTO;

    @BeforeEach
    void beforeEach() {
        pageable = PageRequest.of(0, 55);

        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setTeamName(TEAMNAME);
        footballTeam.setYearOfFoundation(YEAROFFOUNDATION);
        footballTeam.setTeamBudget(TEAMBUDGET);
        footballTeam.setId(ID);

        footballTeamDTO = new FootballTeamDTO();
        footballTeamDTO.setTeamName(footballTeam.getTeamName());
        footballTeamDTO.setYearOfFoundation(footballTeam.getYearOfFoundation());
        footballTeamDTO.setTeamBudget(footballTeam.getTeamBudget());
    }

    @Test
    void getAll() {
        Mockito.when(footballTeamService.getAll())
                .thenReturn(List.of(footballTeamDTO, footballTeamDTO));

        ResponseEntity<List<FootballTeamDTO>> response = footballTeamController.getAll();

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void get() {
        Mockito.when(footballTeamService.get(ID))
                .thenReturn(footballTeamDTO);

        ResponseEntity<FootballTeamDTO> response = footballTeamController.get(ID);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(TEAMNAME, response.getBody().getTeamName());
        Assertions.assertEquals(TEAMBUDGET, response.getBody().getTeamBudget());
        Assertions.assertEquals(YEAROFFOUNDATION, response.getBody().getYearOfFoundation());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByTeamBudget() {
        Mockito.when(footballTeamService.getAllByTeamBudget(TEAMBUDGET, pageable))
                .thenReturn(List.of(footballTeamDTO, footballTeamDTO));

        ResponseEntity<List<FootballTeamDTO>> response = footballTeamController.getAllByTeamBudget(TEAMBUDGET, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(TEAMBUDGET, response.getBody().get(0).getTeamBudget());
        Assertions.assertEquals(TEAMBUDGET, response.getBody().get(1).getTeamBudget());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByYearOfFoundation() {
        Mockito.when(footballTeamService.getAllByYearOfFoundation(YEAROFFOUNDATION, pageable))
                .thenReturn(List.of(footballTeamDTO, footballTeamDTO));

        ResponseEntity<List<FootballTeamDTO>> response = footballTeamController
                .getAllByYearOfFoundation(YEAROFFOUNDATION, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(YEAROFFOUNDATION, response.getBody().get(0).getYearOfFoundation());
        Assertions.assertEquals(YEAROFFOUNDATION, response.getBody().get(1).getYearOfFoundation());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void update() {
        Mockito.when(footballTeamService.update(ID, footballTeamDTO))
                .thenReturn(footballTeamDTO);

        ResponseEntity<FootballTeamDTO> updateResponse = footballTeamController.update(ID, footballTeamDTO);

        Assertions.assertNotNull(updateResponse.getBody());
        Assertions.assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
    }

    @Test
    void create() {
        Mockito.when(footballTeamService.create(footballTeamDTO))
                .thenReturn(footballTeamDTO);

        ResponseEntity<FootballTeamDTO> response = footballTeamController.create(footballTeamDTO);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void delete() {
        ResponseEntity<FootballTeamDTO> response = footballTeamController.delete(ID);

        Mockito.verify(footballTeamService).delete(ID);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
