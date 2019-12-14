package com.cricketleague;

import com.csvReader.CSVBuilderException;
import com.csvReader.CSVBuilderFactory;
import com.csvReader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    ArrayList<Batsman> batsmanArrayList;

    public int getCount(String runsFile) throws CricketLeagueException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(runsFile));
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            return icsvBuilder.getCSVFileList(reader,Batsman.class).size();
        } catch (IOException e) {
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.FILE_PROBLEM,"Wrong file inputted");
        } catch (CSVBuilderException e) {
           throw new CricketLeagueException(CricketLeagueException.ExceptionType.BUILDER_EXCEPTION,e.getMessage());
        } catch (RuntimeException e){
            throw new CricketLeagueException(CricketLeagueException.ExceptionType.HEADER_INCORRECT,e.getMessage());
        }
    }
}
