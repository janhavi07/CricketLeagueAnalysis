package com.cricketleague;

import com.opencsv.bean.CsvBindByName;

public class Batsman {
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

    @CsvBindByName(column = "NO", required = true)
    public int noOfOvers;

    @CsvBindByName(column = "HS", required = true)
    public int highScore;

    @CsvBindByName(column = "BF", required = true)
    public int ballsFaced;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fifths;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public Batsman() {
    }

    public Batsman(Batsman iplCSV) {
        player = iplCSV.player;
        average = iplCSV.average;
        strikingRate = iplCSV.strikingRate;
        fifths = iplCSV.fifths;
        fours = iplCSV.fours;
        highScore = iplCSV.highScore;
        hundreds = iplCSV.hundreds;
        innings = iplCSV.innings;
        matches = iplCSV.matches;
        noOfOvers = iplCSV.noOfOvers;
        position = iplCSV.position;
        sixes = iplCSV.sixes;
        ballsFaced = iplCSV.ballsFaced;
    }

    public Batsman(int position, String player, double average, double strikingRate, int matches, int innings, int noOfOvers,
                   int highScore, int ballsFaced, int runs, int hundreds, int fifths, int fours, int sixes) {
        this.position = position;
        this.player = player;
        this.average = average;
        this.strikingRate = strikingRate;
        this.matches = matches;
        this.innings = innings;
        this.noOfOvers = noOfOvers;
        this.highScore = highScore;
        this.ballsFaced = ballsFaced;
        this.runs = runs;
        this.hundreds = hundreds;
        this.fifths = fifths;
        this.fours = fours;
        this.sixes = sixes;
    }
}
