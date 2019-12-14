package com.cricketleague;

public class CricketLeagueException extends Exception {
    public enum ExceptionType {FILE_PROBLEM,POO
    }
    ExceptionType type;

    public CricketLeagueException( ExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}
