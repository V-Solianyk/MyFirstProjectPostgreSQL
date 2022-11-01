package com.example.MyFirstProjectPostgreSQL.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TeamBudgetDTO {
    private BigDecimal ValueTeamBudget;

    private String currencyCode;

    private Long footballTeamId;
}
