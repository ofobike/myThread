package com.nbcb.salary;

public class CreateThread {
    private static  int counter = 0;
    public static void main(String[] args) {
        Thread t = new Thread(null,new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(counter);
                }
            }
            private void add(int i ){
                counter++;
                add(i++);
            }
        },"TestCount",1<<24);
        t.start();
    }
}
