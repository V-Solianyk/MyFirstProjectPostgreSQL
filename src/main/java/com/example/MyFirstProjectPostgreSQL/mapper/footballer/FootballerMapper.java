package com.example.MyFirstProjectPostgreSQL.mapper.footballer;


import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;

public interface FootballerMapper {
FootballerDTO footballerToFootballerDTO(Footballer footballer);
Footballer footballerDTOToFootballer(FootballerDTO footballerModel);
}
