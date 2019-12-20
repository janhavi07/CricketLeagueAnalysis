package com.cricketleague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sort {
    public enum sortfields{AVG_BATTING_RATE,STRIKING_RATE,FOURS_SIXES}

    static Map<sortfields, Comparator> compareField= new HashMap<>();

    public Comparator getField(Sort.sortfields... sortField) {
        Comparator<Batsman> avgComparator =Comparator.comparing(player -> player.average);
        Comparator<Batsman> strikingComparator =Comparator.comparing(player -> player.strikingRate);
        Comparator<Batsman> fourComparator=Comparator.comparing(player -> player.fours*4+player.sixes*6);
       // Comparator<Batsman> sixesComparator=Comparator.comparing(player -> player.sixes);
        compareField.put(sortfields.AVG_BATTING_RATE,avgComparator);
        compareField.put(sortfields.STRIKING_RATE,strikingComparator);
        compareField.put(sortfields.FOURS_SIXES,fourComparator);
       // compareField.put(sortfields.SIXES,sixesComparator);
        Comparator<Batsman> comparator=compareField.get(sortField[0]);
        return comparator;
    }

    }

