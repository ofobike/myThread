package com.nbcb.log;

public class Log4j implements MyLog{
    @Override
    public void log(String name) {
        System.out.println("Log4j--->{}"+name);
    }
}
