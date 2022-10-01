package com.example.MyFirstProjectPostgreSQL.service;

import com.example.MyFirstProjectPostgreSQL.model.FootballerModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballerService {
    List<FootballerModel> getAll();

    List<FootballerModel> getAllByAge(Integer age, Pageable pageable);

    List<FootballerModel> getAllByOverallRating(Integer overallRating, Pageable pageable);

    List<FootballerModel> getAllByWorkingLegAndAge(String leg, Integer age, Pageable pageable);

    FootballerModel get(Integer id);

    FootballerModel create(FootballerModel footballerModel);

    FootballerModel update(Integer id, FootballerModel footballerModel);

    FootballerModel delete(Integer id);


}
