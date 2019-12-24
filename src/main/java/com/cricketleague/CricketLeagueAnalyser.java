package com.cricketleague;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {
    private Object Players;

    public enum Players {BATSMAN, BOWLERS}

    Map<String, IPLDao> iplList;

    public CricketLeagueAnalyser() {
        this.iplList = new HashMap<>();
    }

    public CricketLeagueAnalyser(Object player) {
        this.Players = player;
    }

    ;

    public int loadData(CricketLeagueAnalyser.Players players, String... csvFile) throws CricketLeagueException {
        IPLAdapter iplAdapter = new IPLAdapterFactory().getIPLAdapter(players);
        iplList = iplAdapter.loadPLayerData(csvFile);
        return iplList.size();
    }

    public String toSort(Sortfield... sortfields) {
        Comparator<IPLDao> comparator = null;
        if (sortfields.length == 2) {
            comparator = new Sort().getField(sortfields[0]).thenComparing(new Sort().getField(sortfields[1]));
            return this.getSortedString(comparator);
        }
        comparator = new Sort().getField(sortfields[0]);
        return this.getSortedString(comparator);
    }

    private String getSortedString(Comparator<IPLDao> comparator) {
        ArrayList iplDTO = iplList.values().stream()
                .sorted(comparator)
                .map(IPLDao -> IPLDao.getIPLDao(Players))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedData = new Gson().toJson(iplDTO);
        return sortedData;
    }

}

