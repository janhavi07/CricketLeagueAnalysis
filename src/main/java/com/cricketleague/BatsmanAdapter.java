package com.cricketleague;

import java.util.Map;

public class BatsmanAdapter extends IPLAdapter {
    @Override
    protected <E> Map<String, IPLDao> loadPLayerData(String csvFile) throws CricketLeagueException {
        Map<String, IPLDao> iplList=super.loadData(Batsman.class,csvFile);
        return iplList;
    }
}
