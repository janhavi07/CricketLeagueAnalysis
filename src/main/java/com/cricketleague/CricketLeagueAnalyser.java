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
    Map<String,Batsman> batsmanList;

    public CricketLeagueAnalyser(){
        this.batsmanList=new HashMap<>();
    }

    public int loadData(String runsFile) throws CricketLeagueException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(runsFile));
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<Batsman> csvIterator=icsvBuilder.getCSVFileIterator(reader,Batsman.class);
            Iterable<Batsman> batsmanIterable=()-> csvIterator;
            StreamSupport.stream(batsmanIterable.spliterator(), false).
                    map(Batsman.class::cast).
                    forEach(iplCSV -> batsmanList.put(iplCSV.player, new Batsman(iplCSV)));
            return batsmanList.size();
        } catch (IOException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.FILE_PROBLEM,"Wrong file inputted");
        } catch (CSVBuilderException e) {
           throw new CricketLeagueException(CricketLeagueException.ExceptionType.BUILDER_EXCEPTION,e.getMessage());
        } catch (RuntimeException e){
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.HEADER_INCORRECT,"Incorrect Header or Delimiter");
        }
    }

    public String toSort(Sort.sortfields... sortfields) {
        Comparator<Batsman> comparator = null;
        if(sortfields.length==2){
            comparator=new Sort().getField(sortfields[0]).thenComparing(new Sort().getField(sortfields[1]));
            return this.getSortedString(comparator);
        }
        comparator=new Sort().getField(sortfields[0]);
        return this.getSortedString(comparator);
    }

    private String getSortedString(Comparator<Batsman> CSVComparator){
        ArrayList c = batsmanList.values().stream()
                .sorted(CSVComparator)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedData = new Gson().toJson(c);
        return sortedData;
    }
}
