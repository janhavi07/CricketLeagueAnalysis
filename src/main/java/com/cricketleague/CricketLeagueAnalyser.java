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
    List<Batsman> batsmanList=new ArrayList<>();

    public int getCount(String runsFile) throws CricketLeagueException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(runsFile));
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            batsmanList=icsvBuilder.getCSVFileList(reader,Batsman.class);
            return batsmanList.size();
        } catch (IOException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.FILE_PROBLEM,"Wrong file inputted");
        } catch (CSVBuilderException e) {
           throw new CricketLeagueException(CricketLeagueException.ExceptionType.BUILDER_EXCEPTION,e.getMessage());
        } catch (RuntimeException e){
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.HEADER_INCORRECT,"Incorrect Header or Delimiter");
        }
    }

    public String getBattingAverage() {
        Comparator<Batsman> comparator =Comparator.comparing(player -> player.average);
        ArrayList c=batsmanList.stream()
                 .sorted(comparator)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedData=new Gson().toJson(c);
        return sortedData;
    }
}
