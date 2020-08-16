package com.nbcb.salary;

public class StackError {
    private static int counter = 0;

    public static void main(String[] args) {
            try{
                add(0);
            }catch (Error e){
                e.printStackTrace();
                System.out.println(counter);
            }
    }
    private static void add(int i){
        ++counter;
        add( i +1);
    }
}
