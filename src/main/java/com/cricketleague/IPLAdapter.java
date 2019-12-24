package com.cricketleague;

import com.csvReader.CSVBuilderException;
import com.csvReader.CSVBuilderFactory;
import com.csvReader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {
    protected abstract <E> Map<String, IPLDao> loadPLayerData(String... csvFile) throws CricketLeagueException;

    Map<String, IPLDao> iplList = new HashMap<>();

    public <E> Map<String, IPLDao> loadData(Class<E> csvClass, String csvFile) throws CricketLeagueException {
        try {

            Reader reader = Files.newBufferedReader(Paths.get(csvFile));
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = icsvBuilder.getCSVFileIterator(reader, csvClass);
            Iterable<E> Iterable = () -> csvIterator;
            if (csvClass.getName().equals("com.cricketleague.Batsman")) {
                StreamSupport.stream(Iterable.spliterator(), false).
                        map(Batsman.class::cast).
                        forEach(iplCSV -> iplList.put(iplCSV.player, new IPLDao(iplCSV)));
            } else if (csvClass.getName().equals("com.cricketleague.Bowlers")) {
                StreamSupport.stream(Iterable.spliterator(), false).
                        map(Bowlers.class::cast).
                        forEach(iplCSV -> iplList.put(iplCSV.player, new IPLDao(iplCSV)));
                System.out.println(iplList.size());
            }
            return iplList;
        } catch (IOException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.FILE_PROBLEM, "Wrong file inputted");
        } catch (CSVBuilderException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.BUILDER_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.HEADER_INCORRECT, "Incorrect Header or Delimiter");
        }
    }
}
