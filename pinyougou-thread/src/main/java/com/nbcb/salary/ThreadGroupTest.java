package com.nbcb.salary;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Optional;

public class ThreadGroupTest {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try{
                Thread.sleep(5000L);
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1");
        t.start();
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
