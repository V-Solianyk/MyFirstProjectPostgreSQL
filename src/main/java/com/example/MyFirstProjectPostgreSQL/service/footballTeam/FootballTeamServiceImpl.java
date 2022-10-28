package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.mapper.footballTeam.FootballTeamMapper;
import com.example.MyFirstProjectPostgreSQL.repository.footballTeam.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballTeamServiceImpl implements FootballTeamService {
    private final FootballTeamRepository footballTeamRepository;
    private final FootballTeamMapper footballTeamMapper;

    @Autowired
    public FootballTeamServiceImpl(FootballTeamRepository footballTeamRepository, FootballTeamMapper footballTeamMapper) {
        this.footballTeamRepository = footballTeamRepository;
        this.footballTeamMapper = footballTeamMapper;
    }

    @Override
    public List<FootballTeamDTO> getAll() {
        return footballTeamRepository.findAll().stream()
                .map(footballTeamMapper::footballTeamToFootballTeamDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FootballTeamDTO get(Long id) {
        FootballTeam footballTeam = footballTeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The football team does not exist for this ID!"));

        return footballTeamMapper.footballTeamToFootballTeamDTO(footballTeam);
    }

    @Override
    public List<FootballTeamDTO> getAllByTeamBudget(long teamBudget, Pageable pageable) {
        return footballTeamRepository.findAllByTeamBudget(teamBudget, pageable).stream()
                .map(footballTeamMapper::footballTeamToFootballTeamDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FootballTeamDTO> getAllByYearOfFoundation(int yearOfFoundation, Pageable pageable) {
        return footballTeamRepository.findAllByYearOfFoundation(yearOfFoundation, pageable).stream()
                .map(footballTeamMapper::footballTeamToFootballTeamDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FootballTeamDTO create(FootballTeamDTO footballTeamDTO) {
        FootballTeam footballTeam = footballTeamMapper.footballTeamDTOToFootballTeam(footballTeamDTO);

        footballTeamRepository.save(footballTeam);

        return footballTeamDTO;
    }

    @Override
    public FootballTeamDTO update(Long id, FootballTeamDTO footballTeamDTO) {
        footballTeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The football team does not exist for this ID!"));

        FootballTeam footballTeam = footballTeamMapper.footballTeamDTOToFootballTeam(footballTeamDTO);
        footballTeam.setId(id);
        footballTeamRepository.save(footballTeam);

        return footballTeamDTO;
    }

    @Override
    public void delete(Long id) {
        footballTeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The football team does not exist for this ID!"));

        footballTeamRepository.deleteById(id);
    }
}
