package com.cricketleague;

public class CricketLeagueException extends Exception {
    public enum ExceptionType {FILE_PROBLEM,BUILDER_EXCEPTION,HEADER_INCORRECT
    }
    ExceptionType type;

    public CricketLeagueException( ExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}
