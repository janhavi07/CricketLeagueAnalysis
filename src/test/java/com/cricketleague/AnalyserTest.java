package com.cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AnalyserTest {

    private static final String BATSMAN_FILE = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String WRONG_FILE = "./src/test/resources/IPL2019FactsheetRuns.csv";
    private static final String INCORRECT_DELIMITER_FILE = "./src/test/resources/IncorrectDelimiterIPL2019FactsheetMostRuns.csv";
    private static final String SAMPLE_BOWLER_FILE = "/home/admin1/Desktop/Janhavi/CricketLeagueAnalysis/src/test/resources/SampleBowlerData.csv";
    private static final String BOWLER_FILE = "/home/admin1/Desktop/Janhavi/CricketLeagueAnalysis/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenTheBatsmanFile_ShouldReturnTheTotalCountOfProgram() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        int getCount = 0;
        try {
            IPLAdapter iplAdapter = CricketLeagueAnalyser.getIplAdapter(CricketLeagueAnalyser.Players.BATSMAN);
            cricketLeagueAnalyser.setIplAdapter(IPLAdapterFactory.getIPLAdapter(CricketLeagueAnalyser.Players.BATSMAN));
            getCount = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            Assert.assertEquals(100, getCount);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFile_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.setIplAdapter(IPLAdapterFactory.getIPLAdapter(CricketLeagueAnalyser.Players.BATSMAN));
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, WRONG_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenFileWithIncorrectDelimiter_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.setIplAdapter(IPLAdapterFactory.getIPLAdapter(CricketLeagueAnalyser.Players.BATSMAN));
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, INCORRECT_DELIMITER_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGiveTopBattingAverage() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.BATTING_AVG_RATE);
            Batsman[] batsmen = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("David Warner", batsmen[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldSortTheData_AndGivePLayerWithHigh_StrikingRate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.STRIKING_RATE);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Ishant Sharma", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_ShouldSortAndGiveCricketer_WithMaximum6s_And4s() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.FOURS_SIXES);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Andre Russell", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_BestStrikingRate_With6S_4S() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.BEST_STRIKING_RATE_WITH6S_4S);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("Ishant Sharma", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_CricketersWithBestAverages_BestStriking() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.BATTING_AVG_RATE, Sortfield.STRIKING_RATE);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("David Warner", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLFile_CricketersWithMaximumRuns_WithBestAverages() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.MAXIMUM_RUNS, Sortfield.BATTING_AVG_RATE);
            Batsman[] ipl = new Gson().fromJson(sortedList, Batsman[].class);
            Assert.assertEquals("David Warner", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenTheBowlersFile_ShouldReturnTheTotalCountOfProgram() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        int getCount = 0;
        try {
            getCount = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, BOWLER_FILE);
            Assert.assertEquals(99, getCount);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongBowlerFile_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, WRONG_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenBowlerFileWithIncorrectDelimiter_ShouldThrowException() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, INCORRECT_DELIMITER_FILE);
        } catch (CricketLeagueException e) {
            Assert.assertEquals(CricketLeagueException.ExceptionType.HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenBowlerFile_ShouldSortTheDataAnd_Bowlers_withTopBowlingAverage() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, SAMPLE_BOWLER_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.BOWLING_AVG_RATE);
            Bowlers[] ipl = new Gson().fromJson(sortedList, Bowlers[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveBowlerFile_ShouldSortTheData_AndGivePLayerWithHigh_StrikingRate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, SAMPLE_BOWLER_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.STRIKING_RATE);
            Bowlers[] ipl = new Gson().fromJson(sortedList, Bowlers[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveBowlerFile_ShouldSortTheData_AndGivePLayerWith_BestEconomyRate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, SAMPLE_BOWLER_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.ECONOMY_RATE);
            Bowlers[] ipl = new Gson().fromJson(sortedList, Bowlers[].class);
            Assert.assertEquals("Lasith Malinga", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveBowlerFile_ShouldSortTheData_AndGivePLayerWith_FourWaND5W() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, SAMPLE_BOWLER_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.FIVEW_FOURW, Sortfield.STRIKING_RATE);
            Bowlers[] ipl = new Gson().fromJson(sortedList, Bowlers[].class);
            Assert.assertEquals("Lasith Malinga", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveBowlerFile_ShouldSortTheData_AndGiveBestBowlingAverage_WithBestStriking_Rate() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, SAMPLE_BOWLER_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.BOWLING_AVG_RATE, Sortfield.STRIKING_RATE);
            Bowlers[] ipl = new Gson().fromJson(sortedList, Bowlers[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveBowlerFile_ShouldSortTheData_AndMaximumWicketsWithBest_BowlingAverage() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BOWLERS);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BOWLERS, SAMPLE_BOWLER_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.WICKETS, Sortfield.BOWLING_AVG_RATE);
            Bowlers[] ipl = new Gson().fromJson(sortedList, Bowlers[].class);
            Assert.assertEquals("Imran Tahir", ipl[0].player);
        } catch (CricketLeagueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveBothTheFiles_GivesBestBattingAndBowlingRates() {
        CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Players.BATSMAN);
        try {
            cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.Players.BATSMAN, BATSMAN_FILE, BOWLER_FILE);
            String sortedList = cricketLeagueAnalyser.toSort(Sortfield.MAXIMUM_RUNS, Sortfield.WICKETS);
            IPLDao[] ipl = new Gson().fromJson(sortedList, IPLDao[].class);
            Assert.assertEquals("David Warner", ipl[0].playerName);
        } catch (CricketLeagueException e) {
            e.printStackTrace();

        }
    }

}

