package com.company.micron;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static private int a = 3;
    static private int b = 8;
    static private int h_org = 7;  //modulo zu a
    static private int o_org = 19;  //modulo zu b


    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
       try{
           System.out.println("Gegeben ein Gleichungssystem von Kongruenzen folgender Form:");
        System.out.println("x \u2261 a  (mod h)");
        System.out.println("x \u2261 b  (mod o)");
        System.out.println();
        System.out.print("Wert für a: ");
        a=user.nextInt();
        System.out.print("Wert für b: ");
        b=user.nextInt();
        System.out.print("Wert für h: ");
        h_org=user.nextInt();
        System.out.print("Wert für 0: ");
        o_org=user.nextInt();
        System.out.println();
        System.out.println("Berechnung:");
        System.out.println("EEA:");
       }
       catch (InputMismatchException e){
           System.err.println("Keine gültige Zahl eingegeben.");
           return;
       }

        int a_l = 1;
        int b_l = 0;
        int h = h_org;
        int o = o_org;
        int h_inv = 0;
        int o_inv = 0;
        int i = 0;
        int[] q = new int[20];
        while (h != 0 && o != 0) {

            if (h > o) {
                if (o != 0) q[i] = (int) h / o;
                System.out.println(i + ". : " + h + "  |   " + o + "  |   " + q[i] + "  |   " + "x" + "  |   " + "x");
                int old = o;
                o = h % o;
                h = old;
            }
            if (h < o) {
                if (h != 0) q[i] = (int) o / h;
                System.out.println(i + ". : " + h + "  |   " + o + "  |   " + q[i] + "  |   " + "x" + "  |   " + "x");
                int old = h;
                h = o % h;
                o = old;
            }

            i++;
        }
        System.out.println("ggt: " + h + "  |   " + o + "  |   ");
        System.out.println();

        for (int j = 0; j <= i; j++) {
            int save = b_l;
            b_l = a_l;
            a_l = save - q[i - j] * a_l;
            System.out.println(j + ". : " + "x" + "  |   " + "x" + "  |   " + q[i - j] + "  |   " + b_l + "  |   " + a_l);
        }
        System.out.println();
        System.out.println("Chinesischer Restsatz:");

        if (o_org > h_org) {
            h_inv = (a_l + o_org) % o_org;
            o_inv = (b_l + h_org) % h_org;
            System.out.println("Inverse h': " + a_l + "+" + o_org + " = " + h_inv);
            System.out.println("Inverse o': " + b_l + "+" + h_org + " = " + o_inv);
        }
        if (o_org < h_org) {
            h_inv = (b_l + o_org) % o_org;
            o_inv = (a_l + h_org) % h_org;
            System.out.println("Inverse h': " + b_l + "+" + o_org + " = " + h_inv);
            System.out.println("Inverse o': " + a_l + "+" + h_org + " = " + o_inv);
        }

        //Probe der Inversen
        if (((h_org * h_inv) % o_org != 1) || ((o_org * o_inv) % h_org != 1)) {
            System.err.println("Die Inversen sind falsch!");
        }
        int y = (a * o_org * o_inv + b * h_org * h_inv);
        int x= y% (o_org * h_org);
        System.out.print(a + "*" + o_org + "*" + o_inv + " + " + b + "*" + h_org +
                "*" + h_inv + " mod " + h_org * o_org + " = " +y+ " mod " + h_org * o_org +" = "+ x);
        System.out.println();
        System.out.println();
        //Probe der Kongruenzen
        if (((x) % h_org != a) || ((x) % o_org != b)) {
            System.out.println();
            System.err.println("Die Kongruenzen sind falsch!");

        }
    }

}