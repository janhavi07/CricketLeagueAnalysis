package com.cricketleague;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPLMockitoTest {
    private static final String BATSMAN_FILE = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String BOWLER_FILE = "/home/admin1/Desktop/Janhavi/CricketLeagueAnalysis/src/test/resources/" +
                                                                             "IPL2019FactsheetMostWkts.csv";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    public Map<String,IPLDao> map;

    public void create(){
        this.map=new HashMap<>();
        this.map.put("janhavi",new IPLDao("hi",0.1));
        this.map.put("parte",new IPLDao("hey",20.0));
        this.map.put("ahds",new IPLDao("hello",23.2));
    }

    @Test
    public void givenIplBatsmanFile_ShouldReturnCorrectCount() {
        IPLAdapter iplAdapter = mock(IPLAdapter.class);
        try{
            create();
           when(iplAdapter.loadPLayerData(BATSMAN_FILE)).thenReturn(this.map);
           CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
           cricketLeagueAnalyser.setIplAdapter(iplAdapter);
            int i = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            Assert.assertEquals(3,i);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIplBowlerFile_ShouldReturnCorrectCount() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try{
            create();
            IPLAdapter iplAdapter = mock(IPLAdapter.class);
            when(iplAdapter.loadPLayerData(BOWLER_FILE)).thenReturn(this.map);
            cricketLeagueAnalyser.setIplAdapter(iplAdapter);
            int i = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, BOWLER_FILE);
            Assert.assertEquals(3,i);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

}
