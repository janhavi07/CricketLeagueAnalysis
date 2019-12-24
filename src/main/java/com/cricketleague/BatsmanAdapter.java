package com.cricketleague;

import com.csvReader.CSVBuilderException;
import com.csvReader.CSVBuilderFactory;
import com.csvReader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdapter extends IPLAdapter {
    @Override
    protected <E> Map<String, IPLDao> loadPLayerData(String... csvFile) throws CricketLeagueException {
        if(csvFile.length==2){
            Map<String, IPLDao> iplList = super.loadData(Batsman.class, csvFile[0]);
            return this.loadBowlerData(iplList,csvFile[1]);
        }
        Map<String, IPLDao> iplList = super.loadData(Batsman.class, csvFile[0]);
        return iplList;
    }

    protected Map<String,IPLDao> loadBowlerData(Map<String, IPLDao> iplList, String bowlerFile) throws CricketLeagueException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(bowlerFile));
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Bowlers> csvIterator = icsvBuilder.getCSVFileIterator(reader, Bowlers.class);
            Iterable<Bowlers> Iterable = () -> csvIterator;
            StreamSupport.stream(Iterable.spliterator(), false)
                    .filter(csvBowler -> iplList.get(csvBowler.player) != null)
                    .forEach(csvBowler -> {
                        iplList.get(csvBowler.player).bowlingAverage = csvBowler.average;
                        iplList.get(csvBowler.player).wickets=csvBowler.wickets;
                    });
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
