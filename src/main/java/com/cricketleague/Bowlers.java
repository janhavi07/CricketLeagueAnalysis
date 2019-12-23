package com.cricketleague;

import com.opencsv.bean.CsvBindByName;

public class Bowlers {

    @CsvBindByName(column = "POS")
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double noOfOvers;

    @CsvBindByName(column = "Runs", required = true)
    public int highScore;

    @CsvBindByName(column = "Wkts", required = true)
    public int ballsFaced;

    @CsvBindByName(column = "BBI",required = true)
    public int runs;

    @CsvBindByName(column = "Econ", required = true)
    public double fifths;

    @CsvBindByName(column = "SR", required = true)
    public double strikingRate;


    @CsvBindByName(column = "4w", required = true)
    public int fours;

    @CsvBindByName(column = "5w", required = true)
    public int fives;

}
