package org.example.all;

public class StringSwap {
    public static void main(String[] args) {
        String str="vidyasagar";
        String p1=str.substring(0,5);
        String p2=str.substring(5);

        System.out.println("P1::"+p1);
        System.out.println("P2::"+p2);

        char ch1=p1.charAt(4);
        char ch2=p2.charAt(0);

        System.out.println("Ch1::"+ch1);
        System.out.println("Ch2::"+ch2);

        String s = p1.substring(0, 4) + ch2 + ch1 + p2.substring(1, 4);
        System.out.println(s);
    }
}
