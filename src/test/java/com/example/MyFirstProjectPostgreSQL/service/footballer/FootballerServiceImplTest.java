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

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
        footballTeam = new FootballTeam();
        footballTeam.setId(1L);
        footballTeamDTO = new FootballTeamDTO();

        footballer = new Footballer();
        footballer.setId(100L);
        footballer.setAge(35);
        footballer.setOverallRating(91);
        footballer.setWorkingLegIsRight(false);

        footballerDTO = new FootballerDTO();
        footballerDTO.setAge(35);
        footballerDTO.setOverallRating(91);
        footballerDTO.setWorkingLegIsRight(false);
        footballerDTO.setFootballTeamId(footballTeam.getId());

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

    @Test
    void getAllByWorkingLegIsRightAndAge() {
        Integer age = 35;

        Boolean workingLegIsRight = false;

        Mockito.when(footballerRepository.findAllByWorkingLegIsRightAndAge(workingLegIsRight, age, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> response = footballerService.getAllByWorkingLegIsRightAndAge(workingLegIsRight, age, pageable);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(age, response.get(0).getAge());
        Assertions.assertEquals(age, response.get(1).getAge());
        Assertions.assertEquals(workingLegIsRight, response.get(0).getWorkingLegIsRight());
        Assertions.assertEquals(workingLegIsRight, response.get(1).getWorkingLegIsRight());
    }

    @Test
    void getAllByFootballTeamId() {
        Long footballTeamId = 1L;

        Mockito.when(footballerRepository.findAllByFootballTeamId(footballTeamId, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByFootballTeamId = footballerService.getAllByFootballTeamId(footballTeamId, pageable);

        Assertions.assertEquals(2, allByFootballTeamId.size());
        Assertions.assertEquals(footballTeamId, allByFootballTeamId.get(0).getFootballTeamId());
        Assertions.assertEquals(footballTeamId, allByFootballTeamId.get(1).getFootballTeamId());
    }

    @Test
    void getNotExistId() {
        long notExistId = 99L;

        String expectedMassage = "The footballer not found for this id: " + notExistId;

        Mockito.when(footballerRepository.findById(notExistId)).thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerService.get(notExistId));

        Assertions.assertEquals(expectedMassage, notFoundException.getMessage());
    }

    @Test
    void getExistId() {
        Long existId = 100L;

        Mockito.when(footballerRepository.findById(existId)).thenReturn(Optional.of(footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(footballer)).thenReturn(footballerDTO);

        FootballerDTO response = footballerService.get(existId);

        Assertions.assertEquals(35, response.getAge());
        Assertions.assertEquals(91, response.getOverallRating());
        Assertions.assertEquals(false, response.getWorkingLegIsRight());
    }

    @Test
    void createWithoutFootballTeamId() {
        Mockito.when(footballerMapper.footballerDTOToFootballer(footballerDTO)).thenReturn(footballer);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class))).thenReturn(footballer);

        FootballerDTO response = footballerService.create(footballerDTO);

        Assertions.assertEquals(35, response.getAge());
        Assertions.assertEquals(91, response.getOverallRating());
        Assertions.assertEquals(false, response.getWorkingLegIsRight());
    }

    @Test
    void createWithFootballTeamId() {

        Mockito.when(footballerMapper.footballerDTOToFootballer(footballerDTO)).thenReturn(footballer);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class))).thenReturn(footballer);

//        Mockito.when(footballTeamRepository.findById(footballerDTO.getFootballTeamId()))


    }
}
