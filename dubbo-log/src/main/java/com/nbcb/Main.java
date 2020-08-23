package com.nbcb;

import com.nbcb.log.MyLog;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<MyLog> serviceLoader = ServiceLoader.load(MyLog.class);
        Iterator<MyLog> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            MyLog next = iterator.next();
            System.out.println(next);
            next.log("JDK SPI");
        }

    }
}
