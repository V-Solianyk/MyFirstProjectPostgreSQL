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
    private static final int AGE = 35;
    private static final int OVERALLRATING = 91;
   private static final Boolean WORKINGLEGISRIGHT = false;
   private static final Long EXISTID = 100L;
   private static final Long NOTEXISTID = 666L;

    @BeforeEach
    void before() {
        footballTeam = new FootballTeam();
        footballTeam.setId(1L);
        footballTeamDTO = new FootballTeamDTO();

        footballer = new Footballer();
        footballer.setId(EXISTID);
        footballer.setAge(AGE);
        footballer.setOverallRating(OVERALLRATING);
        footballer.setWorkingLegIsRight(WORKINGLEGISRIGHT);

        footballerDTO = new FootballerDTO();
        footballerDTO.setAge(footballer.getAge());
        footballerDTO.setOverallRating(footballer.getOverallRating());
        footballerDTO.setWorkingLegIsRight(footballer.getWorkingLegIsRight());

        pageable = PageRequest.of(0, 100);
    }

    @Test
    void getAll() {
        Mockito.when(footballerRepository.findAll())
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> response = footballerService.getAll();

        Assertions.assertEquals(2, response.size());
        Assertions.assertNotNull(response.get(0));
        Assertions.assertNotNull(response.get(1));
    }

    @Test
    void getAllByAge() {
        Mockito.when(footballerRepository.findAllByAge(AGE, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByAge = footballerService.getAllByAge(AGE, pageable);

        Assertions.assertEquals(2, allByAge.size());
        Assertions.assertEquals(AGE, allByAge.get(0).getAge());
        Assertions.assertEquals(AGE, allByAge.get(1).getAge());
    }

    @Test
    void getAllByOverallRating() {
        Mockito.when(footballerRepository.findAllByOverallRating(OVERALLRATING, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> allByOverallRating = footballerService.getAllByOverallRating(OVERALLRATING, pageable);

        Assertions.assertEquals(2, allByOverallRating.size());
        Assertions.assertEquals(OVERALLRATING, allByOverallRating.get(0).getOverallRating());
        Assertions.assertEquals(OVERALLRATING, allByOverallRating.get(1).getOverallRating());
    }

    @Test
    void getAllByWorkingLegIsRight() {
        Mockito.when(footballerRepository.findAllByWorkingLegIsRight(WORKINGLEGISRIGHT, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO, footballerDTO);

        List<FootballerDTO> response = footballerService.getAllByWorkingLegIsRight(WORKINGLEGISRIGHT, pageable);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.get(0).getWorkingLegIsRight());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.get(1).getWorkingLegIsRight());
    }

    @Test
    void getAllByWorkingLegIsRightAndAge() {
        Mockito.when(footballerRepository.findAllByWorkingLegIsRightAndAge(WORKINGLEGISRIGHT, AGE, pageable))
                .thenReturn(List.of(footballer, footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(Mockito.any(Footballer.class)))
                .thenReturn(footballerDTO);

        List<FootballerDTO> response = footballerService.getAllByWorkingLegIsRightAndAge(WORKINGLEGISRIGHT, AGE, pageable);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(AGE, response.get(0).getAge());
        Assertions.assertEquals(AGE, response.get(1).getAge());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.get(0).getWorkingLegIsRight());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.get(1).getWorkingLegIsRight());
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
        String expectedMassage = "The footballer not found for this id: " + NOTEXISTID;

        Mockito.when(footballerRepository.findById(NOTEXISTID))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerService.get(NOTEXISTID));

        Assertions.assertEquals(expectedMassage, notFoundException.getMessage());
    }

    @Test
    void getExistId() {
        Mockito.when(footballerRepository.findById(EXISTID))
                .thenReturn(Optional.of(footballer));

        Mockito.when(footballerMapper.footballerToFootballerDTO(footballer))
                .thenReturn(footballerDTO);

        FootballerDTO response = footballerService.get(EXISTID);

        Assertions.assertEquals(AGE, response.getAge());
        Assertions.assertEquals(OVERALLRATING, response.getOverallRating());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.getWorkingLegIsRight());
    }

    @Test
    void createWithoutFootballTeamId() {
        Mockito.when(footballerMapper.footballerDTOToFootballer(footballerDTO))
                .thenReturn(footballer);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class)))
                .thenReturn(footballer);

        FootballerDTO response = footballerService.create(footballerDTO);

        Assertions.assertEquals(AGE, response.getAge());
        Assertions.assertEquals(OVERALLRATING, response.getOverallRating());
        Assertions.assertEquals(WORKINGLEGISRIGHT, response.getWorkingLegIsRight());
    }

    @Test
    void createWithFootballTeamId() {
        footballerDTO.setFootballTeamId(footballTeam.getId());
        footballer.setFootballTeam(footballTeam);

        Mockito.when(footballerMapper.footballerDTOToFootballer(footballerDTO))
                .thenReturn(footballer);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class)))
                .thenReturn(footballer);

        Mockito.when(footballTeamRepository.findById(footballerDTO.getFootballTeamId()))
                .thenReturn(Optional.of(footballTeam));

        Assertions.assertEquals(footballer.getFootballTeam(), footballTeam);
        Assertions.assertTrue(footballTeam.getFootballers().contains(footballer));
        Mockito.verify(footballTeamRepository).save(footballTeam);
    }

    @Test
    void updateNotExistId() {
        String expectedMessage = "The footballer not found for id: " + NOTEXISTID;

        Mockito.when(footballerRepository.findById(NOTEXISTID))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> footballerService.update(NOTEXISTID, footballerDTO));

        Assertions.assertEquals(expectedMessage, notFoundException.getMessage());
    }

    @Test
    void updateExistIdAndWithoutFootballTeamId() {
        Mockito.when(footballerRepository.findById(EXISTID))
                .thenReturn(Optional.of(footballer));

        Mockito.when(footballerMapper.footballerDTOToFootballer(footballerDTO))
                .thenReturn(footballer);

        Mockito.when(footballerRepository.save(Mockito.any(Footballer.class)))
                .thenReturn(footballer);

        FootballerDTO updateResult = footballerService.update(EXISTID, footballerDTO);

        Assertions.assertEquals(AGE, updateResult.getAge());
        Assertions.assertEquals(OVERALLRATING, updateResult.getOverallRating());
        Assertions.assertEquals(WORKINGLEGISRIGHT, updateResult.getWorkingLegIsRight());
    }

    @Test
    void updateExistIdAndWithFootballTeamId() {
        //todo
    }

}
