package com.cricketleague;

import com.opencsv.bean.CsvBindByName;

public class Bowlers {
    @CsvBindByName(column = "POS")
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikingRate;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double noOfOvers;

    @CsvBindByName(column = "HS", required = true)
    public int highScore;

    @CsvBindByName(column = "BF", required = true)
    public int ballsFaced;

    @CsvBindByName(column = "Runs",required = true)
    public int runs;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fifths;

    @CsvBindByName(column = "4w", required = true)
    public int fours;

    @CsvBindByName(column = "5w", required = true)
    public int sixes;

}
