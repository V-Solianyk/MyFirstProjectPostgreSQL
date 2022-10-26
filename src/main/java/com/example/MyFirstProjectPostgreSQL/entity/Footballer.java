package com.example.MyFirstProjectPostgreSQL.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@Entity
@Table(name = "footballers")
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The footballer surname can not be null or blank.")
    private String surname;

    @Min(16)
    @Max(40)
    private Integer age;

    @Min(45)
    @Max(99)
    private Integer overallRating;

    private Boolean workingLegIsRight = true;

    @ManyToOne
    @JoinColumn(name = "footballTeamId")
    private FootballTeam footballTeam;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Footballer that = (Footballer) o;
        return Objects.equals(id, that.id) && Objects.equals(surname, that.surname) && Objects.equals(age, that.age)
                && Objects.equals(overallRating, that.overallRating) && Objects.equals(workingLegIsRight,
                that.workingLegIsRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, age, overallRating, workingLegIsRight);
    }
}
