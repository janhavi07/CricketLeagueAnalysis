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
            getCount = cricketLeagueAnalyser.getCount(RUNS_FILE);
            Assert.assertEquals(101, getCount);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFile_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.getCount(WRONG_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenFileWithIncorrectDelimiter_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.getCount(INCORRECT_DELIMITER_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGiveTopBattingAverage() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.getCount(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sort.sortfields.AVG_BATTING_RATE);
            Batsman[] censusCSV = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[100].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGivePLayerWithHigh_StrikingRate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.getCount(RUNS_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sort.sortfields.STRIKING_RATE);
            Batsman[] censusCSV = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[100].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }
}

