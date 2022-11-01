package com.example.MyFirstProjectPostgreSQL.conroller.footballTeam;

import com.example.MyFirstProjectPostgreSQL.controller.FootballTeamController;
import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
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
    private static final int YEAROFFOUNDATION = 1899;

    private Pageable pageable;

    private TeamBudgetDTO teamBudgetDTO;

    private FootballTeamDTO footballTeamDTO;

    @BeforeEach
    void beforeEach() {
        pageable = PageRequest.of(0, 55);

        footballTeamDTO = new FootballTeamDTO();
        footballTeamDTO.setYearOfFoundation(YEAROFFOUNDATION);

        teamBudgetDTO = new TeamBudgetDTO();
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
    void updateTeamBudget() {
        Mockito.when(footballTeamService.updateTeamBudget(ID, teamBudgetDTO))
                .thenReturn(teamBudgetDTO);

        ResponseEntity<TeamBudgetDTO> response = footballTeamController.updateTeamBudget(ID, teamBudgetDTO);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
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
    void createTeamBudget() {
        Mockito.when(footballTeamService.createTeamBudget(teamBudgetDTO))
                .thenReturn(teamBudgetDTO);

        ResponseEntity<TeamBudgetDTO> response = footballTeamController.createTeamBudget(teamBudgetDTO);

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
