package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.entity.TeamBudget;
import com.example.MyFirstProjectPostgreSQL.mapper.footballTeam.FootballTeamMapper;
import com.example.MyFirstProjectPostgreSQL.mapper.teamBudget.TeamBudgetMapper;
import com.example.MyFirstProjectPostgreSQL.repository.FootballTeamRepository;
import com.example.MyFirstProjectPostgreSQL.repository.TeamBudgetRepository;
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
    private final TeamBudgetRepository teamBudgetRepository;
    private final TeamBudgetMapper teamBudgetMapper;

    @Autowired
    public FootballTeamServiceImpl(FootballTeamRepository footballTeamRepository, FootballTeamMapper footballTeamMapper,
                                   TeamBudgetRepository teamBudgetRepository, TeamBudgetMapper teamBudgetMapper) {
        this.footballTeamRepository = footballTeamRepository;
        this.footballTeamMapper = footballTeamMapper;
        this.teamBudgetRepository = teamBudgetRepository;
        this.teamBudgetMapper = teamBudgetMapper;
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

    @Override
    public TeamBudgetDTO createTeamBudget(TeamBudgetDTO teamBudgetDTO) {
        FootballTeam footballTeam = footballTeamRepository.findById(teamBudgetDTO.getFootballTeamId())
                .orElseThrow(() -> new EntityNotFoundException("The football team does not exist for this id."));

        TeamBudget teamBudget = teamBudgetMapper.teamBudgetDTOToTeamBudget(teamBudgetDTO);

        teamBudget.setFootballTeam(footballTeam);
        footballTeamRepository.save(footballTeam);

        footballTeam.setTeamBudget(teamBudget);
        teamBudgetRepository.save(teamBudget);


        return teamBudgetDTO;
    }

    @Override
    public TeamBudgetDTO updateTeamBudget(Long id, TeamBudgetDTO teamBudgetDTO) {
        teamBudgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The team budget does not exist for this id."));

        TeamBudget teamBudget = teamBudgetMapper.teamBudgetDTOToTeamBudget(teamBudgetDTO);
        teamBudget.setId(id);
        teamBudgetRepository.save(teamBudget);

        return teamBudgetDTO;
    }
}
