package com.cricketleague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sort {
    public enum sortfields {AVG_BATTING_RATE, STRIKING_RATE, FOURS_SIXES, BEST_STRIKING_RATE_WITH6S_4S}

    static Map<sortfields, Comparator> compareField = new HashMap<>();

    public Comparator getField(Sort.sortfields... sortField) {
        Comparator<Batsman> avgComparator = Comparator.comparing(player -> player.average);
        Comparator<Batsman> strikingComparator = Comparator.comparing(player -> player.strikingRate);
        Comparator<Batsman> fourComparator = Comparator.comparing(player -> player.fours * 4 + player.sixes * 6);
        Comparator<Batsman> bestStriking6S4S = Comparator.comparing(player -> (player.fours * 4 + player.sixes * 6) / player.ballsFaced);
        compareField.put(sortfields.AVG_BATTING_RATE ,avgComparator.reversed());
        compareField.put(sortfields.STRIKING_RATE, strikingComparator.reversed());
        compareField.put(sortfields.FOURS_SIXES, fourComparator.reversed());
        compareField.put(sortfields.BEST_STRIKING_RATE_WITH6S_4S, bestStriking6S4S.reversed());
        Comparator<Batsman> comparator = compareField.get(sortField[0]);
        return comparator;
    }
}

