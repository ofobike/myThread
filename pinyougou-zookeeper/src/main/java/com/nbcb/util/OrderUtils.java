package com.nbcb.util;

import java.util.UUID;

public class OrderUtils {
    public static String getOrderIdByUUId(){
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodev = UUID.randomUUID().toString().hashCode();
        if(hashCodev < 0){
            //有可能是负数
            hashCodev = -hashCodev;
        }
        //"%015d"的意思：0代表不足位数的补0，这样可以确保相同的位数，15是位数也就是要得到到的字符串长度是15，d代表数字。
        return machineId + String.format("%015d", hashCodev);
    }


    public static void main(String[] args) {
        for (int i = 0;i<100000;i++){
            String orderIdByUUId = getOrderIdByUUId();
            System.out.println(orderIdByUUId);
        }
    }

    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    public static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }
    private static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
}
