package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.mapper.footballTeam.FootballTeamMapper;
import com.example.MyFirstProjectPostgreSQL.repository.footballTeam.FootballTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class FootballTeamServiceImplTest {
    FootballTeamRepository footballTeamRepository = Mockito.mock(FootballTeamRepository.class);
    FootballTeamMapper footballTeamMapper = Mockito.mock(FootballTeamMapper.class);
    FootballTeamServiceImpl footballTeamService = new FootballTeamServiceImpl(footballTeamRepository, footballTeamMapper);

    private FootballTeam footballTeam;
    private FootballTeamDTO footballTeamDTO;
    private Pageable pageable;
    private static final String TEAMNAME = "Barcelona";
    private static final int YEAROFFOUNDATION = 1899;
    private static final long TEAMBUDGET = 100000000;
    private static final Long EXISTID = 100L;
    private static final Long NOTEXISTID = 10L;

    @BeforeEach
    void beforeEach() {
        footballTeam = new FootballTeam();
        footballTeam.setId(EXISTID);
        footballTeam.setTeamName(TEAMNAME);
        footballTeam.setYearOfFoundation(YEAROFFOUNDATION);
        footballTeam.setTeamBudget(TEAMBUDGET);

        footballTeamDTO = new FootballTeamDTO();
        footballTeamDTO.setTeamName(footballTeam.getTeamName());
        footballTeamDTO.setYearOfFoundation(footballTeam.getYearOfFoundation());
        footballTeamDTO.setTeamBudget(footballTeam.getTeamBudget());

        pageable = PageRequest.of(0, 22);
    }

    @Test
    void getAll() {
        Mockito.when(footballTeamRepository.findAll())
                .thenReturn(List.of(footballTeam, footballTeam));

        Mockito.when(footballTeamMapper.footballTeamToFootballTeamDTO(Mockito.any(FootballTeam.class)))
                .thenReturn(footballTeamDTO);

        List<FootballTeamDTO> response = footballTeamService.getAll();

        Assertions.assertEquals(2, response.size());
        Assertions.assertNotNull(response.get(0));
        Assertions.assertNotNull(response.get(1));
    }

    @Test
    void getExistId() {
        Mockito.when(footballTeamRepository.findById(EXISTID))
                .thenReturn(Optional.of(footballTeam));

        Mockito.when(footballTeamMapper.footballTeamToFootballTeamDTO(footballTeam))
                .thenReturn(footballTeamDTO);

        FootballTeamDTO response = footballTeamService.get(EXISTID);

        Assertions.assertEquals(TEAMNAME, response.getTeamName());
        Assertions.assertEquals(YEAROFFOUNDATION, response.getYearOfFoundation());
        Assertions.assertEquals(TEAMBUDGET, response.getTeamBudget());
    }

    @Test
    void getNotExistId() {
        String expected = "The football team does not exist for this ID!";

        Mockito.when(footballTeamRepository.findById(NOTEXISTID))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballTeamService.get(NOTEXISTID));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void ExistId() {
        Mockito.when(footballTeamRepository.findById(EXISTID))
                .thenReturn(Optional.of(footballTeam));

        Mockito.when(footballTeamMapper.footballTeamToFootballTeamDTO(footballTeam))
                .thenReturn(footballTeamDTO);

        FootballTeamDTO response = footballTeamService.get(EXISTID);

        Assertions.assertEquals(TEAMNAME, response.getTeamName());
        Assertions.assertEquals(TEAMBUDGET, response.getTeamBudget());
        Assertions.assertEquals(YEAROFFOUNDATION, response.getYearOfFoundation());
    }

    @Test
    void getAllByTeamBudget() {
        Mockito.when(footballTeamRepository.findAllByTeamBudget(TEAMBUDGET, pageable))
                .thenReturn(List.of(footballTeam, footballTeam));

        Mockito.when(footballTeamMapper.footballTeamToFootballTeamDTO(Mockito.any(FootballTeam.class)))
                .thenReturn(footballTeamDTO);

        List<FootballTeamDTO> response = footballTeamService.getAllByTeamBudget(TEAMBUDGET, pageable);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(TEAMBUDGET, response.get(0).getTeamBudget());
        Assertions.assertEquals(TEAMBUDGET, response.get(1).getTeamBudget());
    }

    @Test
    void getAllByYearOfFoundation() {
        Mockito.when(footballTeamRepository.findAllByYearOfFoundation(YEAROFFOUNDATION, pageable))
                .thenReturn(List.of(footballTeam, footballTeam));

        Mockito.when(footballTeamMapper.footballTeamToFootballTeamDTO(Mockito.any(FootballTeam.class)))
                .thenReturn(footballTeamDTO);

        List<FootballTeamDTO> response = footballTeamService.getAllByYearOfFoundation(YEAROFFOUNDATION, pageable);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(YEAROFFOUNDATION, response.get(0).getYearOfFoundation());
        Assertions.assertEquals(YEAROFFOUNDATION, response.get(1).getYearOfFoundation());
    }

    @Test
    void create() {
        Mockito.when(footballTeamMapper.footballTeamDTOToFootballTeam(footballTeamDTO))
                .thenReturn(footballTeam);

        Mockito.when(footballTeamRepository.save(Mockito.any(FootballTeam.class)))
                .thenReturn(footballTeam);

        FootballTeamDTO response = footballTeamService.create(footballTeamDTO);

        Assertions.assertEquals(TEAMNAME, response.getTeamName());
        Assertions.assertEquals(TEAMBUDGET, response.getTeamBudget());
        Assertions.assertEquals(YEAROFFOUNDATION, response.getYearOfFoundation());
    }

    @Test
    void updateNotExistId() {
        String expected = "The football team does not exist for this ID!";

        Mockito.when(footballTeamRepository.findById(NOTEXISTID))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballTeamService.update(NOTEXISTID, footballTeamDTO));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void updateExistId() {
        Mockito.when(footballTeamRepository.findById(EXISTID))
                .thenReturn(Optional.of(footballTeam));

        Mockito.when(footballTeamMapper.footballTeamDTOToFootballTeam(footballTeamDTO))
                .thenReturn(footballTeam);

        Mockito.when(footballTeamRepository.save(Mockito.any(FootballTeam.class)))
                .thenReturn(footballTeam);

        FootballTeamDTO responseExistId = footballTeamService.update(EXISTID, footballTeamDTO);

        Assertions.assertEquals(TEAMNAME, responseExistId.getTeamName());
        Assertions.assertEquals(TEAMBUDGET, responseExistId.getTeamBudget());
        Assertions.assertEquals(YEAROFFOUNDATION, responseExistId.getYearOfFoundation());
    }

    @Test
    void deleteNotExistId() {
        String expected = "The football team does not exist for this ID!";

        Mockito.when(footballTeamRepository.findById(NOTEXISTID))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballTeamService.delete(NOTEXISTID));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void deleteExistId() {
        Mockito.when(footballTeamRepository.findById(EXISTID))
                .thenReturn(Optional.of(footballTeam));

        footballTeamService.delete(EXISTID);

        Mockito.verify(footballTeamRepository).deleteById(EXISTID);
    }
}
