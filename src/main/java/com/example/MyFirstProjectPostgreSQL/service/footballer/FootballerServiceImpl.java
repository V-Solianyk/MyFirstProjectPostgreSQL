package com.example.MyFirstProjectPostgreSQL.service.footballer;

import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.mapper.footballer.FootballerMapper;
import com.example.MyFirstProjectPostgreSQL.model.FootballerModel;
import com.example.MyFirstProjectPostgreSQL.repository.footballer.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public List<FootballerModel> getAll() {
        return StreamSupport.stream(footballerRepository.findAll().spliterator(), false)
                .map(footballerMapper::footballerToFootballerModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerModel> getAllByAge(Integer age, Pageable pageable) {
        return footballerRepository.findAllByAge(age, pageable).stream()
                .map(footballerMapper::footballerToFootballerModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerModel> getAllByOverallRating(Integer overallRating, Pageable pageable) {
        return footballerRepository.findAllByOverallRating(overallRating, pageable).stream()
                .map(footballerMapper::footballerToFootballerModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerModel> getAllByWorkingLeg(String leg, Pageable pageable) {
        return footballerRepository.findAllByWorkingLeg(leg, pageable).stream()
                .map(footballerMapper::footballerToFootballerModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerModel> getAllByWorkingLegAndAge(String leg, Integer age, Pageable pageable) {
        return footballerRepository.findAllByWorkingLegAndAge(leg, age, pageable).stream()
                .map(footballerMapper::footballerToFootballerModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballerModel> getAllByFootballTeam(String footballTeam, Pageable pageable) {
        return footballerRepository.findAllByFootballTeam(footballTeam, pageable).stream()
                .map(footballerMapper::footballerToFootballerModel)
                .collect(Collectors.toList());
    }

    @Override
    public FootballerModel get(Long id) {
        Footballer footballer = footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Footballer not found for id: " + id));

        return footballerMapper.footballerToFootballerModel(footballer);
    }

    @Override
    public FootballerModel create(FootballerModel footballerModel) {
        Footballer footballer = footballerMapper.footballerModelToFootballer(footballerModel);

        footballerRepository.save(footballer);

        return footballerModel;
    }

    @Override
    public FootballerModel update(Long id, FootballerModel footballerModel) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Footballer not found for id: " + id));

        Footballer footballer = footballerMapper.footballerModelToFootballer(footballerModel);
        footballer.setId(id);

        return footballerModel;
    }

    @Override
    public void delete(Long id) {
        footballerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Footballer does not exist for this ID."));

        footballerRepository.deleteById(id);
    }
}
