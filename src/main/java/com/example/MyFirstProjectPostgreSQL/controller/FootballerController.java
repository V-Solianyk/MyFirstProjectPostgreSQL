package com.example.MyFirstProjectPostgreSQL.controller;

import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.service.footballer.FootballerService;
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
@RequestMapping("/footballers")
public class FootballerController {
    private final FootballerService footballerService;

    @Autowired
    public FootballerController(FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @GetMapping
    ResponseEntity<List<FootballerDTO>> getAll() {
        return ResponseEntity.ok(footballerService.getAll());
    }

    @GetMapping("/age")
    ResponseEntity<List<FootballerDTO>> getAllByAge(@RequestParam("age") Integer age, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByAge(age, pageable));
    }

    @GetMapping("/overallRating")
    ResponseEntity<List<FootballerDTO>> getAllByOverallRating(@RequestParam("overallRating") Integer overallRating,
                                                              Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByOverallRating(overallRating, pageable));
    }

    @GetMapping("/workingLegIsRightAndAge")
    ResponseEntity<List<FootballerDTO>> getAllByWorkingLegAndAge(@RequestParam("workingLegIsRight") Boolean workingLegIsRight,
                                                                 @RequestParam("age") Integer age, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByWorkingLegIsRightAndAge(workingLegIsRight, age, pageable));
    }

    @GetMapping("/workingLegIsRight")
    ResponseEntity<List<FootballerDTO>> getAllByWorkingLegIsRight
            (@RequestParam("workingLegIsRight") Boolean workingLegIsRight, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByWorkingLegIsRight(workingLegIsRight, pageable));
    }

    @GetMapping("/footballTeamId")
    ResponseEntity<List<FootballerDTO>> getAllByFootballTeamId(@RequestParam("footballTeamId") Long footballTeamId,
                                                               Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByFootballTeamId(footballTeamId, pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<FootballerDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(footballerService.get(id));
    }

    @PostMapping
    ResponseEntity<FootballerDTO> create(@RequestBody FootballerDTO footballerDTO) {
        return new ResponseEntity<FootballerDTO>(footballerService.create(footballerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<FootballerDTO> update(@PathVariable Long id, @RequestBody FootballerDTO footballerDTO) {
        return ResponseEntity.ok(footballerService.update(id, footballerDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<FootballerDTO> delete(@PathVariable Long id) {
        footballerService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
