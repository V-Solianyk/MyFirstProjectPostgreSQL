package com.example.MyFirstProjectPostgreSQL.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Data
public class TeamBudget {
    @Id
    @Column(name = "footballTeam_id")
    private Long id;

    private BigDecimal ValueTeamBudget;

    private Currency currency;

    @OneToOne
    @MapsId
    @JoinColumn(name = "footballTeam_id")
    private FootballTeam footballTeam;
}
