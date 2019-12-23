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
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "BBI",required = true)
    public int bbi;

    @CsvBindByName(column = "Econ", required = true)
    public double econ;

    @CsvBindByName(column = "SR", required = true)
    public double strikingRate;

    @CsvBindByName(column = "4w", required = true)
    public int fours;

    @CsvBindByName(column = "5w", required = true)
    public int fives;

    public Bowlers(int position, String player, double average, int matches, int innings, double noOfOvers, int runs, int wickets,
                   int bbi, double econ, double strikingRate, int fours, int fives) {
        this.position = position;
        this.player = player;
        this.average = average;
        this.matches = matches;
        this.innings = innings;
        this.noOfOvers = noOfOvers;
        this.runs = runs;
        this.wickets = wickets;
        this.bbi = bbi;
        this.econ = econ;
        this.strikingRate = strikingRate;
        this.fours = fours;
        this.fives = fives;
    }
}
