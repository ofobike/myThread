package com.nbcb.log;

public class Logback implements MyLog{
    @Override
    public void log(String name) {
        System.out.println("LogBack-->{}"+name);
    }

}
