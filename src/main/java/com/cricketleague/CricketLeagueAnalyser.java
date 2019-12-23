package com.cricketleague;

import com.csvReader.CSVBuilderException;
import com.csvReader.CSVBuilderFactory;
import com.csvReader.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    private Object Players;
    public enum Players {BATSMAN, BOWLERS}

    Map<String, IPLDao> iplList;

    public CricketLeagueAnalyser() {
        this.iplList = new HashMap<>();
    }
    public CricketLeagueAnalyser(Object player){ this.Players=player;};

    public int loadData(CricketLeagueAnalyser.Players players, String csvFile) throws CricketLeagueException {
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
                .map(IPLDao-> IPLDao.getIPLDao(Players))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedData = new Gson().toJson(iplDTO);
        return sortedData;
    }

    }
//    public String getSort(Sorting.SortField... sortField) {
//        Comparator<CensusDAO> censusCSVComparator = null;
//        if(sortField.length==2) {
//            censusCSVComparator = new Sorting().getField(sortField[0]).thenComparing(new Sorting().getField(sortField[1]));
//            return this.getSortedString(censusCSVComparator);
//        }
//        censusCSVComparator=new Sorting().getField(sortField[0]);
//        return this.getSortedString(censusCSVComparator);
//    }
//
//    private String getSortedString(Comparator<CensusDAO> censusCSVComparator){
//        ArrayList censusDTO=censusStateMap.values().stream()
//                .sorted(censusCSVComparator)
//                .map(censusDAO -> censusDAO.getCensusDTO(country))
//                .collect(Collectors.toCollection(ArrayList::new));
//        String sortedStateCensusJson=new Gson().toJson(censusDTO);
//        return sortedStateCensusJson;
//    }
