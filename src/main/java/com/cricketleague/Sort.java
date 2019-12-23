package com.cricketleague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sort {
    static Map<Sortfield, Comparator> compareField = new HashMap<>();

    public Comparator getField(Sortfield... sortField) {
        Comparator<IPLDao> avgComparator = Comparator.comparing(player -> player.average);
        Comparator<IPLDao> strikingComparator = Comparator.comparing(player -> player.strikingRate);
        Comparator<IPLDao> fourComparator = Comparator.comparing(player -> player.fours * 4 + player.sixes * 6);
        Comparator<IPLDao> bestStriking6S4S = Comparator.comparing(player -> (player.fours * 4 + player.sixes * 6) / player.BF);
        Comparator<IPLDao> maximumRuns=Comparator.comparing(player ->player.runs);
        compareField.put(Sortfield.MAXIMUM_RUNS,maximumRuns.reversed());
        compareField.put(Sortfield.AVG_BATTING_RATE ,avgComparator.reversed());
        compareField.put(Sortfield.STRIKING_RATE, strikingComparator.reversed());
        compareField.put(Sortfield.FOURS_SIXES, fourComparator.reversed());
        compareField.put(Sortfield.BEST_STRIKING_RATE_WITH6S_4S, bestStriking6S4S.reversed());
        Comparator<IPLDao> comparator = compareField.get(sortField[0]);
        return comparator;
    }
}

