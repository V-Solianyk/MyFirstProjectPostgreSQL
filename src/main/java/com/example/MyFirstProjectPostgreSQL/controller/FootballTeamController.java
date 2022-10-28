package com.example.MyFirstProjectPostgreSQL.controller;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
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
    ResponseEntity<List<FootballTeamDTO>> getAll() {
        return ResponseEntity.ok(footballTeamService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<FootballTeamDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(footballTeamService.get(id));
    }

    @GetMapping("/teamBudget")
    ResponseEntity<List<FootballTeamDTO>> getAllByTeamBudget(@RequestParam("teamBudget") long budget, Pageable pageable) {
        return ResponseEntity.ok(footballTeamService.getAllByTeamBudget(budget, pageable));
    }

    @GetMapping("/yearOfFoundation")
    ResponseEntity<List<FootballTeamDTO>> getAllByYearOfFoundation(@RequestParam("yearOfFoundation") int year,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(footballTeamService.getAllByYearOfFoundation(year, pageable));
    }

    @PutMapping("/{id}")
    ResponseEntity<FootballTeamDTO> update(@PathVariable Long id, @RequestBody FootballTeamDTO footballTeamDTO) {
        return ResponseEntity.ok(footballTeamService.update(id, footballTeamDTO));
    }

    @PostMapping
    ResponseEntity<FootballTeamDTO> create(@RequestBody FootballTeamDTO footballTeamDTO) {
        return new ResponseEntity<FootballTeamDTO>(footballTeamService.create(footballTeamDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<FootballTeamDTO> delete(@PathVariable Long id) {
        footballTeamService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
