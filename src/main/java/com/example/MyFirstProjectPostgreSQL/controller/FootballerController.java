package com.example.MyFirstProjectPostgreSQL.controller;

import com.example.MyFirstProjectPostgreSQL.model.FootballerModel;
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
    ResponseEntity<List<FootballerModel>> getAll() {
        return ResponseEntity.ok(footballerService.getAll());
    }

    @GetMapping("/age")
    ResponseEntity<List<FootballerModel>> getAllByAge(@RequestParam("age") Integer age, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByAge(age, pageable));
    }

    @GetMapping("/overallRating")
    ResponseEntity<List<FootballerModel>> getAllByOverallRating(@RequestParam("overallRating") Integer overallRating,
                                                                Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByOverallRating(overallRating, pageable));
    }

    @GetMapping("/overallRatingAndAge")
    ResponseEntity<List<FootballerModel>> getAllByWorkingLegAndAge(@RequestParam("workingLeg") String leg,
                                                                   @RequestParam("age") Integer age, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByWorkingLegAndAge(leg, age, pageable));
    }

    @GetMapping("/workingKeg")
    ResponseEntity<List<FootballerModel>> getAllByWorkingLeg(@RequestParam("workingLeg") String leg, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByWorkingLeg(leg, pageable));
    }

    @GetMapping("/footballTeam")
    ResponseEntity<List<FootballerModel>> getAllByFootballTeam(@RequestParam("footballTeam") String team, Pageable pageable) {
        return ResponseEntity.ok(footballerService.getAllByFootballTeam(team, pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<FootballerModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(footballerService.get(id));
    }

    @PostMapping
    ResponseEntity<FootballerModel> create(@RequestBody FootballerModel footballerModel) {
        return new ResponseEntity<FootballerModel>(footballerService.create(footballerModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<FootballerModel> update(@PathVariable Long id, @RequestBody FootballerModel footballerModel) {
        return ResponseEntity.ok(footballerService.update(id, footballerModel));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<FootballerModel> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
