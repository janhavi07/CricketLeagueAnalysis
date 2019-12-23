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
    Map<String,IPLDao> iplList;

    public CricketLeagueAnalyser() {
        this.iplList = new HashMap<>();
    }

    public int loadBatsmanData(String runsFile) throws CricketLeagueException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(runsFile));
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Batsman> csvIterator = icsvBuilder.getCSVFileIterator(reader, Batsman.class);
            Iterable<Batsman> batsmanIterable = () -> csvIterator;
            StreamSupport.stream(batsmanIterable.spliterator(), false).
                    map(Batsman.class::cast).
                    forEach(iplCSV -> iplList.put(iplCSV.player, new IPLDao(iplCSV)));
            return iplList.size();
        } catch (IOException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.FILE_PROBLEM, "Wrong file inputted");
        } catch (CSVBuilderException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.BUILDER_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.HEADER_INCORRECT, "Incorrect Header or Delimiter");
        }
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

    private String getSortedString(Comparator<IPLDao> CSVComparator) {
        ArrayList c = iplList.values().stream()
                .sorted(CSVComparator)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedData = new Gson().toJson(c);
        return sortedData;
    }

    public int loadBowlersData(String bowlerFile) throws CricketLeagueException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(bowlerFile));
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Bowlers> csvIterator = icsvBuilder.getCSVFileIterator(reader, Bowlers.class);
            Iterable<Bowlers> bowlerIterable = () -> csvIterator;
            StreamSupport.stream(bowlerIterable.spliterator(), false).
                    map(Bowlers.class::cast).
                    forEach(iplCSV -> iplList.put(iplCSV.player, new IPLDao(iplCSV)));
            return iplList.size();
        } catch (IOException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.FILE_PROBLEM, "Wrong file inputted");
        } catch (CSVBuilderException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.BUILDER_EXCEPTION, e.getMessage());
        }

    }
}
