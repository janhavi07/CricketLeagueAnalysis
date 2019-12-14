package com.cricketleague;

import org.junit.Assert;
import org.junit.Test;

public class AnalyserTest {

    private static final String RUNS_FILE="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE="./src/test/resources/IPL2019FactsheetRuns.csv";

    @Test
    public void givenTheRunsFile_ShouldReturnTheTotalCountOfProgram() {
        CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
        int getCount= 0;
        try {
            getCount = cricketLeagueAnalyser.getCount(RUNS_FILE);
            Assert.assertEquals(101,getCount);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFile_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.getCount(WRONG_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.FILE_PROBLEM,e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileWithIncorrectDelimiter_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
        try {
            cricketLeagueAnalyser.getCount(RUNS_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.HEADER_INCORRECT,e.type);
            e.printStackTrace();
        }

        }
    }
