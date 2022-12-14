package com.example.MyFirstProjectPostgreSQL.controller;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
import com.example.MyFirstProjectPostgreSQL.service.footballTeam.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/footballTeams")
public class FootballTeamController {
    private final FootballTeamService footballTeamService;

    @Autowired
    public FootballTeamController(FootballTeamService footballTeamService) {
        this.footballTeamService = footballTeamService;
    }

    @GetMapping
    public ResponseEntity<List<FootballTeamDTO>> getAll() {
        return ResponseEntity.ok(footballTeamService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballTeamDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(footballTeamService.get(id));
    }

    @GetMapping("/yearOfFoundation")
    public ResponseEntity<List<FootballTeamDTO>> getAllByYearOfFoundation(@RequestParam("yearOfFoundation") int year,
                                                                          Pageable pageable) {
        return ResponseEntity.ok(footballTeamService.getAllByYearOfFoundation(year, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FootballTeamDTO> update(@PathVariable Long id, @RequestBody FootballTeamDTO footballTeamDTO) {
        return ResponseEntity.ok(footballTeamService.update(id, footballTeamDTO));
    }

    @PostMapping
    public ResponseEntity<FootballTeamDTO> create(@RequestBody FootballTeamDTO footballTeamDTO) {
        return new ResponseEntity<FootballTeamDTO>(footballTeamService.create(footballTeamDTO), HttpStatus.CREATED);
    }

    @PostMapping("/teamBudget")
    public ResponseEntity<TeamBudgetDTO> createTeamBudget(@RequestBody TeamBudgetDTO teamBudgetDTO) {
        return new ResponseEntity<TeamBudgetDTO>(footballTeamService.createTeamBudget(teamBudgetDTO), HttpStatus.CREATED);
    }

    @PutMapping("/teamBudget/{id}")
    public ResponseEntity<TeamBudgetDTO> updateTeamBudget(@PathVariable Long id, @RequestBody TeamBudgetDTO teamBudgetDTO) {
        return ResponseEntity.ok(footballTeamService.updateTeamBudget(id, teamBudgetDTO));
    }

    public @DeleteMapping("/{id}")
    ResponseEntity<FootballTeamDTO> delete(@PathVariable Long id) {
        footballTeamService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
