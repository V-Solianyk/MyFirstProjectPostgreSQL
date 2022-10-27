package com.example.MyFirstProjectPostgreSQL.service.footballer;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.mapper.footballer.FootballerMapper;
import com.example.MyFirstProjectPostgreSQL.repository.footballTeam.FootballTeamRepository;
import com.example.MyFirstProjectPostgreSQL.repository.footballer.FootballerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class FootballerServiceImplTest {
    FootballerRepository footballerRepository = Mockito.mock(FootballerRepository.class);
    FootballTeamRepository footballTeamRepository = Mockito.mock(FootballTeamRepository.class);
    FootballerMapper footballerMapper = Mockito.mock(FootballerMapper.class);

    FootballerServiceImpl footballerService = new FootballerServiceImpl(footballerRepository, footballerMapper,
            footballTeamRepository);

    private Footballer footballer;
    private FootballerDTO footballerDTO;
    private FootballTeam footballTeam;
    private FootballTeamDTO footballTeamDTO;
    private Pageable pageable;

    @BeforeEach
    void before() {
        footballer = new Footballer();
        footballer.setAge(35);
        footballer.setOverallRating(91);
        footballer.setWorkingLegIsRight(false);

        footballerDTO = new FootballerDTO();
        footballerDTO.setAge(35);
        footballerDTO.setOverallRating(91);
        footballerDTO.setWorkingLegIsRight(false);
        footballTeam = new FootballTeam();

        footballTeamDTO = new FootballTeamDTO();

        pageable = PageRequest.of(0, 100);

    }

    @Test
    void getAll() {
        Mockito.when(footballerRepository.findAll()).thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> response = footballerService.getAll();

        Assertions.assertEquals(2, response.size());
        Assertions.assertNotNull(response.get(0));
        Assertions.assertNotNull(response.get(1));
    }

    @Test
    void getAllByAge() {
        Integer age = 35;

        Mockito.when(footballerRepository.findAllByAge(age, pageable)).thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByAge = footballerService.getAllByAge(age, pageable);

        Assertions.assertEquals(2, allByAge.size());
        Assertions.assertEquals(age, allByAge.get(0).getAge());
        Assertions.assertEquals(age, allByAge.get(1).getAge());
    }

    @Test
    void getAllByOverallRating() {
        Integer overallRating = 91;

        Mockito.when(footballerRepository.findAllByOverallRating(overallRating, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByOverallRating = footballerService.getAllByOverallRating(overallRating, pageable);

        Assertions.assertEquals(2, allByOverallRating.size());
        Assertions.assertEquals(overallRating, allByOverallRating.get(0).getOverallRating());
        Assertions.assertEquals(overallRating, allByOverallRating.get(1).getOverallRating());
    }

    @Test
    void getAllByWorkingLegIsRight() {
        Boolean workingLegIsRight = false;
        Mockito.when(footballerRepository.findAllByWorkingLegIsRight(workingLegIsRight, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO, footballerDTO);

        List<FootballerDTO> response = footballerService.getAllByWorkingLegIsRight(workingLegIsRight, pageable);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(workingLegIsRight, response.get(0).getWorkingLegIsRight());
        Assertions.assertEquals(workingLegIsRight, response.get(1).getWorkingLegIsRight());
    }
}
