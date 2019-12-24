package com.cricketleague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sort {
    static Map<Sortfield, Comparator> compareField = new HashMap<>();

    public Comparator getField(Sortfield... sortField) {
        Comparator<IPLDao> bowlingAvg = Comparator.comparing(player -> player.bowlingAverage);
        Comparator<IPLDao> battingAvg = Comparator.comparing(player -> player.battingAverage);
        Comparator<IPLDao> wickets = Comparator.comparing(player -> player.wickets);
        Comparator<IPLDao> strikingComparator = Comparator.comparing(player -> player.strikingRate);
        Comparator<IPLDao> fourComparator = Comparator.comparing(player -> player.fours * 4 + player.sixes * 6);
        Comparator<IPLDao> fourFiveComparator = Comparator.comparing(player -> player.fours * 4 + player.fives * 5);
        Comparator<IPLDao> bestStriking6S4S = Comparator.comparing(player -> (player.fours * 4 + player.sixes * 6) / player.BF);
        Comparator<IPLDao> maximumRuns=Comparator.comparing(player ->player.runs);
        Comparator<IPLDao> economyRate=Comparator.comparing(player ->player.econ);
        compareField.put(Sortfield.FIVEW_FOURW,fourFiveComparator.reversed());
        compareField.put(Sortfield.MAXIMUM_RUNS,maximumRuns.reversed());
        compareField.put(Sortfield.BOWLING_AVG_RATE ,bowlingAvg.reversed());
        compareField.put(Sortfield.BATTING_AVG_RATE ,battingAvg.reversed());
        compareField.put(Sortfield.STRIKING_RATE, strikingComparator.reversed());
        compareField.put(Sortfield.FOURS_SIXES, fourComparator.reversed());
        compareField.put(Sortfield.BEST_STRIKING_RATE_WITH6S_4S, bestStriking6S4S.reversed());
        compareField.put(Sortfield.ECONOMY_RATE, economyRate.reversed());
        compareField.put(Sortfield.WICKETS,wickets.reversed());
        Comparator<IPLDao> comparator = compareField.get(sortField[0]);
        return comparator;
    }
}

