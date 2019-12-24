package com.cricketleague;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class BowlerAdapterTest {

    private static final String BOWLER_FILE = "/home/admin1/Desktop/Janhavi/CricketLeagueAnalysis/src/test/resources/" +
                                                                                         "IPL2019FactsheetMostWkts.csv";
    @Test
    public void givenBowlerFileFromAdapter_ShouldReturnRecord() {
        BowlerAdapter bowlerAdapter = new BowlerAdapter();
        try {
            Map<String, IPLDao> stringIPLDaoMap = bowlerAdapter.loadPLayerData(BOWLER_FILE);
            Assert.assertEquals(99,stringIPLDaoMap.size());
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }
}
