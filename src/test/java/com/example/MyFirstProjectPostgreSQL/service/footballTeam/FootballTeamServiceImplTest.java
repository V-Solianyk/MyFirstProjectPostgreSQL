package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.entity.TeamBudget;
import com.example.MyFirstProjectPostgreSQL.mapper.footballTeam.FootballTeamMapper;
import com.example.MyFirstProjectPostgreSQL.mapper.teamBudget.TeamBudgetMapper;
import com.example.MyFirstProjectPostgreSQL.repository.FootballTeamRepository;
import com.example.MyFirstProjectPostgreSQL.repository.TeamBudgetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class FootballTeamServiceImplTest {
    FootballTeamRepository footballTeamRepository = Mockito.mock(FootballTeamRepository.class);
    FootballTeamMapper footballTeamMapper = Mockito.mock(FootballTeamMapper.class);
    TeamBudgetRepository teamBudgetRepository = Mockito.mock(TeamBudgetRepository.class);
    TeamBudgetMapper teamBudgetMapper = Mockito.mock(TeamBudgetMapper.class);
    FootballTeamServiceImpl footballTeamService = new FootballTeamServiceImpl(footballTeamRepository, footballTeamMapper,
            teamBudgetRepository, teamBudgetMapper);

    private FootballTeam footballTeam;
    private FootballTeamDTO footballTeamDTO;

    private TeamBudgetDTO teamBudgetDTO;
    private TeamBudget teamBudget;

    private Pageable pageable;

    private static final String TEAMNAME = "Barcelona";
    private static final int YEAROFFOUNDATION = 1899;
    private static final BigDecimal VALUETEAMBUDGET = new BigDecimal(100000000);
    private static final String CURRENCYCODE = "USD";

    private static final Long EXISTID = 100L;
    private static final Long NOTEXISTID = 10L;

    @BeforeEach
    void beforeEach() {
        footballTeam = new FootballTeam();
        footballTeam.setId(EXISTID);
        footballTeam.setTeamName(TEAMNAME);
        footballTeam.setYearOfFoundation(YEAROFFOUNDATION);

        footballTeamDTO = new FootballTeamDTO();
        footballTeamDTO.setTeamName(footballTeam.getTeamName());
        footballTeamDTO.setYearOfFoundation(footballTeam.getYearOfFoundation());

        pageable = PageRequest.of(0, 22);

        teamBudgetDTO = new TeamBudgetDTO();
        teamBudgetDTO.setValueTeamBudget(VALUETEAMBUDGET);
        teamBudgetDTO.setCurrencyCode(CURRENCYCODE);
        teamBudgetDTO.setFootballTeamId(EXISTID);

        teamBudget = new TeamBudget();
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
        Assertions.assertEquals(YEAROFFOUNDATION, response.getYearOfFoundation());
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

    @Test
    void createTeamBudgetForNotExistId() {
        String expected = "The football team does not exist for this id.";

        Mockito.when(footballTeamRepository.findById(NOTEXISTID))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballTeamService.createTeamBudget( teamBudgetDTO));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void createTeamBudgetForExistId() {
        Mockito.when(footballTeamRepository.findById(teamBudgetDTO.getFootballTeamId()))
                .thenReturn(Optional.of(footballTeam));

        Mockito.when(teamBudgetMapper.teamBudgetDTOToTeamBudget(teamBudgetDTO))
                .thenReturn(teamBudget);

        TeamBudgetDTO response = footballTeamService.createTeamBudget(teamBudgetDTO);

        Mockito.verify(footballTeamRepository).save(footballTeam);

        Assertions.assertEquals(VALUETEAMBUDGET, response.getValueTeamBudget());
        Assertions.assertEquals(CURRENCYCODE, response.getCurrencyCode());
    }

    @Test
    void updateTeamBudgetForNotExistId() {
        String expected = "The team budget does not exist for this id.";

        Mockito.when(teamBudgetRepository.findById(EXISTID))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballTeamService.updateTeamBudget(NOTEXISTID, teamBudgetDTO));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void updateTeamBudgetForExistId() {
        Mockito.when(teamBudgetRepository.findById(EXISTID))
                .thenReturn(Optional.of(teamBudget));

        Mockito.when(teamBudgetMapper.teamBudgetDTOToTeamBudget(teamBudgetDTO))
                .thenReturn(teamBudget);

        TeamBudgetDTO response = footballTeamService.updateTeamBudget(EXISTID, teamBudgetDTO);

        Mockito.verify(teamBudgetRepository).save(teamBudget);

        Assertions.assertEquals(VALUETEAMBUDGET, response.getValueTeamBudget());
        Assertions.assertEquals(CURRENCYCODE, response.getCurrencyCode());
    }
}
