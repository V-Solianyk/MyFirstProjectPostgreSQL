package com.example.MyFirstProjectPostgreSQL.mapper;


import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.model.FootballerModel;

public interface FootballerMapper {
FootballerModel footballerToFootballerModel(Footballer footballer);
Footballer footballerModelToFootballer(FootballerModel footballerModel);
}
