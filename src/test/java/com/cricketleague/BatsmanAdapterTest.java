package com.cricketleague;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class BatsmanAdapterTest {

    private static final String BATSMAN_FILE = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenBowlerFileFromAdapter_ShouldReturnRecord() {
        BatsmanAdapter batsmanAdapter = new BatsmanAdapter();
        try {
            Map<String, IPLDao> stringIPLDaoMap = batsmanAdapter.loadPLayerData(BATSMAN_FILE);
            Assert.assertEquals(100,stringIPLDaoMap.size());
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }
}
