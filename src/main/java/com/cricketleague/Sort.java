package com.cricketleague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sort {
    static Map<Sortfield, Comparator> compareField = new HashMap<>();

    public Comparator getField(Sortfield... sortField) {
        Comparator<Batsman> avgComparator = Comparator.comparing(player -> player.average);
        Comparator<Batsman> strikingComparator = Comparator.comparing(player -> player.strikingRate);
        Comparator<Batsman> fourComparator = Comparator.comparing(player -> player.fours * 4 + player.sixes * 6);
        Comparator<Batsman> bestStriking6S4S = Comparator.comparing(player -> (player.fours * 4 + player.sixes * 6) / player.ballsFaced);
        Comparator<Batsman> maximumRuns=Comparator.comparing(player ->player.runs);
        compareField.put(Sortfield.MAXIMUM_RUNS,maximumRuns.reversed());
        compareField.put(Sortfield.AVG_BATTING_RATE ,avgComparator.reversed());
        compareField.put(Sortfield.STRIKING_RATE, strikingComparator.reversed());
        compareField.put(Sortfield.FOURS_SIXES, fourComparator.reversed());
        compareField.put(Sortfield.BEST_STRIKING_RATE_WITH6S_4S, bestStriking6S4S.reversed());
        Comparator<Batsman> comparator = compareField.get(sortField[0]);
        return comparator;
    }
}

