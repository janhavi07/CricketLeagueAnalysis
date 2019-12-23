package com.cricketleague;

public class IPLDao {


    private int matches;
    private int runs;
    private String player;

    public IPLDao(Batsman batsman) {
        player=batsman.player;
        runs=batsman.runs;
        matches = batsman.matches;
    }

    public IPLDao(Bowlers bowlers) {
        player=bowlers.player;
        matches = bowlers.matches;
    }
}
