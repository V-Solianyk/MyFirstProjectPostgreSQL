package com.example.MyFirstProjectPostgreSQL.conroller.footballer;

import com.example.MyFirstProjectPostgreSQL.controller.FootballerController;
import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.service.footballer.FootballerService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FootballerControllerTest {
    FootballerService footballerService = Mockito.mock(FootballerService.class);
    FootballerController footballerController = new FootballerController(footballerService);
    private static final int AGE = 37;
    private static final int OVERALLRATING = 90;
    private static final String SURNAME = "Ronaldo";
    private static final Boolean WORKINGLEGISRIGHT = true;
    private static final Long FOOTBALLTEAMID = 10L;
    private static final Long ID = 11L;
    private static Pageable pageable;
    private FootballerDTO footballerDTO;

    @BeforeEach
    void beforeEach() {
        pageable = PageRequest.of(0, 40);

         FootballTeam footballTeam = new FootballTeam();
        footballTeam.setId(FOOTBALLTEAMID);

        Footballer footballer = new Footballer();
        footballer.setId(ID);
        footballer.setFootballTeam(footballTeam);
        footballer.setAge(AGE);
        footballer.setOverallRating(OVERALLRATING);
        footballer.setSurname(SURNAME);
        footballer.setWorkingLegIsRight(WORKINGLEGISRIGHT);

        footballerDTO = new FootballerDTO();
        footballerDTO.setAge(footballer.getAge());
        footballerDTO.setOverallRating(footballer.getOverallRating());
        footballerDTO.setSurname(footballer.getSurname());
        footballerDTO.setWorkingLegIsRight(footballer.getWorkingLegIsRight());
        footballerDTO.setFootballTeamId(footballer.getFootballTeam().getId());
    }

    @Test
    void getAll() {
        Mockito.when(footballerService.getAll())
                .thenReturn(List.of(footballerDTO, footballerDTO));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAll();

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByAge() {
        Mockito.when(footballerService.getAllByAge(AGE, pageable))
                .thenReturn(List.of(footballerDTO, footballerDTO));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAllByAge(AGE, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(AGE, response.getBody().get(0).getAge());
        Assertions.assertEquals(AGE, response.getBody().get(1).getAge());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByOverallRating() {
        Mockito.when(footballerService.getAllByOverallRating(OVERALLRATING, pageable))
                .thenReturn(List.of(footballerDTO, footballerDTO));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAllByOverallRating(OVERALLRATING, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(OVERALLRATING, response.getBody().get(0).getOverallRating());
        Assertions.assertEquals(OVERALLRATING, response.getBody().get(1).getOverallRating());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllBySurnameContainsIgnoreCase() {
        String keyword = "Ronaldo";
        Mockito.when(footballerService.getAllBySurnameContainsIgnoreCase(keyword, pageable))
                .thenReturn(List.of(footballerDTO, footballerDTO));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAllBySurnameContainsIgnoreCase(keyword,
                pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(response.getBody().get(0).getSurname(), keyword));
        Assertions.assertTrue(StringUtils.containsIgnoreCase(response.getBody().get(1).getSurname(), keyword));
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByWorkingLegIsRightAndAge() {
        Mockito.when(footballerService.getAllByWorkingLegIsRightAndAge(WORKINGLEGISRIGHT, AGE, pageable))
                .thenReturn(List.of(footballerDTO, footballerDTO));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAllByWorkingLegAndAge(WORKINGLEGISRIGHT,
                AGE, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.getBody().get(0).getWorkingLegIsRight());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.getBody().get(1).getWorkingLegIsRight());
        Assertions.assertEquals(AGE, response.getBody().get(0).getAge());
        Assertions.assertEquals(AGE, response.getBody().get(1).getAge());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByWorkingLegIsRight() {
        Mockito.when(footballerService.getAllByWorkingLegIsRight(WORKINGLEGISRIGHT, pageable))
                .thenReturn(List.of(footballerDTO, footballerDTO));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAllByWorkingLegIsRight(WORKINGLEGISRIGHT,
                pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.getBody().get(0).getWorkingLegIsRight());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.getBody().get(1).getWorkingLegIsRight());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllByFootballTeamId() {
        Mockito.when(footballerService.getAllByFootballTeamId(FOOTBALLTEAMID, pageable))
                .thenReturn(List.of(footballerDTO, footballerDTO));

        ResponseEntity<List<FootballerDTO>> response = footballerController.getAllByFootballTeamId(FOOTBALLTEAMID, pageable);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(FOOTBALLTEAMID, response.getBody().get(0).getFootballTeamId());
        Assertions.assertEquals(FOOTBALLTEAMID, response.getBody().get(1).getFootballTeamId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getById() {
        Mockito.when(footballerService.get(ID))
                .thenReturn(footballerDTO);

        ResponseEntity<FootballerDTO> response = footballerController.getById(ID);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        Mockito.when(footballerService.create(footballerDTO))
                .thenReturn(footballerDTO);

        ResponseEntity<FootballerDTO> response = footballerController.create(footballerDTO);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void update() {
        Mockito.when(footballerService.update(ID, footballerDTO))
                .thenReturn(footballerDTO);

        ResponseEntity<FootballerDTO> responseUpdate = footballerController.update(ID, footballerDTO);

        Assertions.assertNotNull(responseUpdate.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseUpdate.getStatusCode());
    }

    @Test
    void delete() {
        ResponseEntity<FootballerDTO> delete = footballerController.delete(ID);

        Mockito.verify(footballerService).delete(ID);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, delete.getStatusCode());
    }
}
