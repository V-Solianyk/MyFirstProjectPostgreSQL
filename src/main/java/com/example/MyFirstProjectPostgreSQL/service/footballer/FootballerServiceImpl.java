package com.example.MyFirstProjectPostgreSQL.service.footballer;

import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.mapper.footballer.FootballerMapper;
import com.example.MyFirstProjectPostgreSQL.repository.footballer.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballerServiceImpl implements FootballerService {
    private final FootballerRepository footballerRepository;
    private final FootballerMapper footballerMapper;

    @Autowired
    public FootballerServiceImpl(FootballerRepository footballerRepository, FootballerMapper footballerMapper) {
        this.footballerRepository = footballerRepository;
        this.footballerMapper = footballerMapper;
    }

    @Override
    public List<FootballerDTO> getAll() {
        return footballerRepository.findAll().stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByAge(Integer age, Pageable pageable) {
        return footballerRepository.findAllByAge(age, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByOverallRating(Integer overallRating, Pageable pageable) {
        return footballerRepository.findAllByOverallRating(overallRating, pageable).stream()
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
    public List<FootballerDTO> getAllByWorkingLegIsRightAndAge(Boolean isRight, Integer age, Pageable pageable) {
        return footballerRepository.findAllByWorkingLegIsRightAndAge(isRight, age, pageable).stream()
                .map(footballerMapper::footballerToFootballerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerDTO> getAllByFootballTeam(FootballTeam footballTeam, Pageable pageable) {
        return footballerRepository.findAllByFootballTeam(footballTeam, pageable).stream()
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

        return footballerDTO;
    }

    @Override
    public FootballerDTO update(Long id, FootballerDTO footballerDTO) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer not found for id: " + id));

        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);
        footballer.setId(id);

        return footballerDTO;
    }

    @Override
    public void delete(Long id) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The footballer does not exist for this ID."));

        footballerRepository.deleteById(id);
    }
}
