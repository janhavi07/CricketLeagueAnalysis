package com.cricketleague;

public class IPLDao {

    public double bowlingAverage;
    public double battingAverage;
    public int fives;
    public double econ;
    public int bbi;
    public int wickets;
    public int fours;
    public int fifths;
    public int hundreds;
    public double strikingRate;
    public int BF;
    public int HS;
    public int noOfOvers;
    public int innings;
    public int position;
    public int sixes;
    public int matches;
    public int runs;
    public String playerName;


    public IPLDao(Batsman batsman) {
        playerName =batsman.player;
        position=batsman.position;
        runs=batsman.runs;
        innings=batsman.innings;
        matches = batsman.matches;
        noOfOvers=batsman.noOfOvers;
        battingAverage=batsman.average;
        HS=batsman.highScore;
        fours= batsman.fours;
        sixes=batsman.sixes;
        BF=batsman.ballsFaced;
        strikingRate=batsman.strikingRate;
        hundreds=batsman.hundreds;
        fifths=batsman.fifths;
    }

    public IPLDao(Bowlers bowlers) {
        position=bowlers.position;
        playerName =bowlers.player;
        matches = bowlers.matches;
        innings=bowlers.innings;
        noOfOvers= (int) bowlers.noOfOvers;
        runs=bowlers.runs;
        wickets=bowlers.wickets;
        bbi=bowlers.bbi;
        bowlingAverage=bowlers.average;
        econ=bowlers.econ;
        strikingRate=bowlers.strikingRate;
        fours=bowlers.fours;
        fives=bowlers.fives;
    }

    public IPLDao(String message, double value) {
    }

    public Object getIPLDao(Object player) {
        if(player.equals(CricketLeagueAnalyser.Players.BATSMAN))
            return new Batsman(position,playerName,battingAverage, strikingRate, matches, innings,noOfOvers,HS,BF,
                    runs,hundreds, fifths,fours, sixes);
        else return new Bowlers(position, playerName,bowlingAverage,matches,innings, noOfOvers, runs, wickets,bbi, econ, strikingRate, fours, fives);
    }
}
