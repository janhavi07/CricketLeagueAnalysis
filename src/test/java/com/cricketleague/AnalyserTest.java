package com.cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AnalyserTest {

    private static final String RUNS_FILE = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE = "./src/test/resources/IPL2019FactsheetRuns.csv";
    private static final String INCORRECT_DELIMITER_FILE = "./src/test/resources/IncorrectDelimiterIPL2019FactsheetMostRuns.csv";
    private static final String BOWLER_FILE= "/home/admin1/Desktop/Janhavi/CricketLeagueAnalysis/src/test/resources/SampleBowlerData.csv";



    @Test
    public void givenTheRunsFile_ShouldReturnTheTotalCountOfProgram() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        int getCount = 0;
        try {
            getCount = cricketLeagueAnalyser.loadBatsmanData(RUNS_FILE);
            Assert.assertEquals(100, getCount);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFile_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(WRONG_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenFileWithIncorrectDelimiter_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(INCORRECT_DELIMITER_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGiveTopBattingAverage() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.AVG_BATTING_RATE);
            Batsman[] censusCSV = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("David Warner", censusCSV[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGivePLayerWithHigh_StrikingRate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.STRIKING_RATE);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Ishant Sharma", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldSortAndGiveCricketer_WithMaximum6s_And4s() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.FOURS_SIXES);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Andre Russell", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_BestStrikingRate_With6S_4S() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.BEST_STRIKING_RATE_WITH6S_4S);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Ishant Sharma", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_CricketersWithBestAverages_BestStriking() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.AVG_BATTING_RATE, Sortfield.STRIKING_RATE);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("David Warner", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_CricketersWithMaximumRuns_WithBestAverages() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadBatsmanData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.MAXIMUM_RUNS, Sortfield.AVG_BATTING_RATE);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("David Warner", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheBowlersFile_ShouldReturnTheTotalCountOfProgram() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        int getCount = 0;
        try {
            getCount = cricketLeagueAnalyser.loadBowlersData(BOWLER_FILE);
            Assert.assertEquals(100, getCount);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }
}

