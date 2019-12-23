package com.cricketleague;

public class IPLAdapterFactory {

    public IPLAdapter getIPLAdapter(CricketLeagueAnalyser.Players players){
        if (players.equals(CricketLeagueAnalyser.Players.BATSMAN))
            return new BatsmanAdapter();
        else
            return new BowlerAdapter();
    }
}
