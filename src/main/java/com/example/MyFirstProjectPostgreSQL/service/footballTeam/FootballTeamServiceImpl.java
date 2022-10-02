package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.mapper.footballTeam.FootballTeamMapper;
import com.example.MyFirstProjectPostgreSQL.model.FootballTeamModel;
import com.example.MyFirstProjectPostgreSQL.repository.footballTeam.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FootballTeamServiceImpl implements FootballTeamService{
    private final FootballTeamRepository footballTeamRepository;
    private final FootballTeamMapper footballTeamMapper;

    @Autowired
    public FootballTeamServiceImpl(FootballTeamRepository footballTeamRepository, FootballTeamMapper footballTeamMapper) {
        this.footballTeamRepository = footballTeamRepository;
        this.footballTeamMapper = footballTeamMapper;
    }

    @Override
    public List<FootballTeamModel> getAll() {
       return StreamSupport.stream(footballTeamRepository.findAll().spliterator(),false)
                .map(footballTeamMapper::footballTeamToFootballTeamModel)
                .collect(Collectors.toList());
    }

    @Override
    public FootballTeamModel get(Long id) {
        return null;
    }

    @Override
    public FootballTeamModel create(FootballTeamModel footballTeamModel) {
        return null;
    }

    @Override
    public FootballTeamModel update(Long id, FootballTeamModel footballTeamModel) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
