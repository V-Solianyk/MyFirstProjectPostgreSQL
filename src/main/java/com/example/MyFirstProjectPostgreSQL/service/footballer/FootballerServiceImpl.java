package com.example.MyFirstProjectPostgreSQL.service.footballer;

import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.mapper.footballer.FootballerMapper;
import com.example.MyFirstProjectPostgreSQL.repository.FootballTeamRepository;
import com.example.MyFirstProjectPostgreSQL.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FootballerServiceImpl implements FootballerService {
    private final FootballerRepository footballerRepository;
    private final FootballerMapper footballerMapper;
    private final FootballTeamRepository footballTeamRepository;

    @Autowired
    public FootballerServiceImpl(FootballerRepository footballerRepository, FootballerMapper footballerMapper,
                                 FootballTeamRepository footballTeamRepository) {
        this.footballerRepository = footballerRepository;
        this.footballerMapper = footballerMapper;
        this.footballTeamRepository = footballTeamRepository;
    }

    @Override
    public List<FootballerDTO> getAll() {
        return footballerRepository.findAll().stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByAge(int age, Pageable pageable) {
        return footballerRepository.findAllByAge(age, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByOverallRating(int overallRating, Pageable pageable) {
        return footballerRepository.findAllByOverallRating(overallRating, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllBySurnameContainsIgnoreCase(String keyword, Pageable pageable) {
        return footballerRepository.findAllBySurnameContainsIgnoreCase(keyword, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByWorkingLegIsRight(Boolean isRight, Pageable pageable) {
        return footballerRepository.findAllByWorkingLegIsRight(isRight, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByWorkingLegIsRightAndAge(Boolean isRight, int age, Pageable pageable) {
        return footballerRepository.findAllByWorkingLegIsRightAndAge(isRight, age, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByFootballTeamId(Long footballTeamId, Pageable pageable) {
        return footballerRepository.findAllByFootballTeamId(footballTeamId, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FootballerDTO get(Long id) {
        Footballer footballer = footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer not found for this id: " + id));

        return footballerMapper.footballerToFootballerDTO(footballer);
    }

    @Override
    public FootballerDTO create(FootballerDTO footballerDTO) {
        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);
        footballerRepository.save(footballer);

        if (footballerDTO.getFootballTeamId() != null) {
            footballTeamRepository.findById(footballerDTO.getFootballTeamId())
                    .ifPresent(footballTeam -> {
                        footballer.setFootballTeam(footballTeam);
                        Set<Footballer> footballers = footballTeam.getFootballers();
                        footballers.add(footballer);
                        footballTeam.setFootballers(footballers);
                        footballTeamRepository.save(footballTeam);
                    });
        }

        return footballerDTO;
    }

    @Override
    public FootballerDTO update(Long id, FootballerDTO footballerDTO) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer not found for id: " + id));

        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);
        footballer.setId(id);
        footballerRepository.save(footballer);

        if (footballerDTO.getFootballTeamId() != null) {
            footballTeamRepository.findById(footballerDTO.getFootballTeamId())
                    .ifPresent(footballTeam -> {
                        footballer.setFootballTeam(footballTeam);
                        Set<Footballer> footballers = footballTeam.getFootballers();
                        footballers.add(footballer);
                        footballTeam.setFootballers(footballers);
                        footballTeamRepository.save(footballTeam);
                    });
        }
        return footballerDTO;
    }

    @Override
    public void delete(Long id) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer does not exist for this ID."));

        footballerRepository.deleteById(id);
    }
}
