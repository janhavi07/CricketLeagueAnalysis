package com.cricketleague;

import com.opencsv.bean.CsvBindByName;

public class PlayerRuns {

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Avg",required = true)
    public double average;

}
