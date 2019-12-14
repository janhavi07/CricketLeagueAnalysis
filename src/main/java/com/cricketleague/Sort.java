package com.cricketleague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sort {
    public enum sortfields{AVG_BATTING_RATE,STRIKING_RATE}

    static Map<sortfields, Comparator> compareField= new HashMap<>();

    public Comparator getField(Sort.sortfields sortField) {
        Comparator<Batsman> avgComparator =Comparator.comparing(player -> player.average);
        Comparator<Batsman> strikingComparator =Comparator.comparing(player -> player.strikingRate);
        compareField.put(sortfields.AVG_BATTING_RATE,avgComparator);
        compareField.put(sortfields.STRIKING_RATE,strikingComparator);
        Comparator<Batsman> comparator=compareField.get(sortField);
        return comparator;
    }

    }

