package com.cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AnalyserTest {

    private static final String RUNS_FILE = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE = "./src/test/resources/IPL2019FactsheetRuns.csv";
    private static final String INCORRECT_DELIMITER_FILE = "./src/test/resources/IncorrectDelimiterIPL2019FactsheetMostRuns.csv";


    @Test
    public void givenTheRunsFile_ShouldReturnTheTotalCountOfProgram() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        int getCount = 0;
        try {
            getCount = cricketLeagueAnalyser.loadData(RUNS_FILE);
            Assert.assertEquals(100, getCount);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFile_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadData(WRONG_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenFileWithIncorrectDelimiter_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadData(INCORRECT_DELIMITER_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGiveTopBattingAverage() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sort.sortfields.AVG_BATTING_RATE);
            Batsman[] censusCSV = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[99].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGivePLayerWithHigh_StrikingRate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sort.sortfields.STRIKING_RATE);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Ishant Sharma", ipl[99].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldSortAndGiveCricketer_WithMaximum6s_And4s() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sort.sortfields.FOURS_SIXES);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Andre Russell", ipl[99].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_BestStrikingRate_With6S_4S() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.loadData(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sort.sortfields.BEST_STRIKING_RATE_WITH6S_4S);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Ishant Sharma", ipl[99].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }
}

