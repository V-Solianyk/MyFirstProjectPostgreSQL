package com.example.MyFirstProjectPostgreSQL.service;

import com.example.MyFirstProjectPostgreSQL.mapper.FootballerMapper;
import com.example.MyFirstProjectPostgreSQL.model.FootballerModel;
import com.example.MyFirstProjectPostgreSQL.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FootballerServiceImpl implements FootballerService{
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
        return null;
    }

    @Override
    public List<FootballerModel> getAllByOverallRating(Integer overallRating, Pageable pageable) {
        return null;
    }

    @Override
    public List<FootballerModel> getAllByWorkingLegAndAge(String leg, Integer age, Pageable pageable) {
        return null;
    }

    @Override
    public FootballerModel get(Integer id) {
        return null;
    }

    @Override
    public FootballerModel create(FootballerModel footballerModel) {
        return null;
    }

    @Override
    public FootballerModel update(Integer id, FootballerModel footballerModel) {
        return null;
    }

    @Override
    public FootballerModel delete(Integer id) {
        return null;
    }
}
