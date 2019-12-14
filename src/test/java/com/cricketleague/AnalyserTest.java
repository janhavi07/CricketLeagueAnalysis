package com.cricketleague;

import org.junit.Assert;
import org.junit.Test;

public class AnalyserTest {

    private static final String RUNS_FILE="./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenTheRunsFile_ShouldReturnTheTotalCountOfProgram() {
        CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
        int getCount=cricketLeagueAnalyser.getCount(RUNS_FILE);
        Assert.assertEquals(101,getCount);

    }
}
