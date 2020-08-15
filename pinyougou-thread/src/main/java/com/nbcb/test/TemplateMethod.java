package com.nbcb.test;

public class TemplateMethod {
    public final void print(String message){
        System.out.println("==================");
        wrapPrint(message);
        System.out.println("==================");
    }

    protected void wrapPrint(String message) {
    }

    public static void main(String[] args) {
        TemplateMethod method = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }
        };
        method.print("HelloWorld");
    }
}
