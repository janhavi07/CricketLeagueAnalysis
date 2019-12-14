package com.cricketleague;

import com.csvReader.CSVBuilderException;
import com.csvReader.CSVBuilderFactory;
import com.csvReader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {

    public int getCount(String runsFile) throws CricketLeagueException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(runsFile));
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<Batsman> csvFileIterator = icsvBuilder.getCSVFileIterator(reader,Batsman.class);
            Iterable<Batsman> csvIterable = () -> csvFileIterator;
            int namOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return namOfEateries;
        } catch (IOException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.FILE_PROBLEM,"Wrong file inputted");
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
