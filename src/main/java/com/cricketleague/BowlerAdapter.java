package com.cricketleague;

import java.util.Map;

public class BowlerAdapter extends IPLAdapter {
    @Override
    protected <E> Map<String, IPLDao> loadPLayerData(String... csvFile) throws CricketLeagueException {
        Map<String, IPLDao> iplList=super.loadData(Bowlers.class,csvFile[0]);
        return iplList;
    }
}
