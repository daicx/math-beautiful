package com.skrtu.math.string;

import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {

    public static Integer strToInt(String str) {
        int length = str.length();
        int result = 0;
        for (int i = length - 1; i >= 0; i--) {
            String tmp = String.valueOf(str.charAt(i));
            String s = tmp.toLowerCase(Locale.ROOT);
            int powj = length - i - 1;
            int tmpi = 0;
            if (s.equalsIgnoreCase("a")) {
                tmpi = 10;
            } else if (s.equalsIgnoreCase("b")) {
                tmpi = 11;
            } else if (s.equalsIgnoreCase("c")) {
                tmpi = 12;
            } else if (s.equalsIgnoreCase("d")) {
                tmpi = 13;
            } else if (s.equalsIgnoreCase("e")) {
                tmpi = 14;
            } else if (s.equalsIgnoreCase("f")) {
                tmpi = 15;
            } else {
                tmpi = Integer.parseInt(s);
            }
            result += tmpi * Math.pow(2, powj);

        }
        return result;

    }

    //2
    //00001001 00ABCD00
    //3
    //FFFFFAAB FFFFFAAB 00ABCD00
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count1 = sc.nextInt();
        String str1 = "";
        for (int i = 0; i < count1; i++) {
            String s = sc.nextLine();
            str1 += s;
        }
        String str2 = "";
        int count2 = sc.nextInt();
        while (sc.hasNext()){
            for (int i = 0; i < count2; i++) {
                String s = sc.nextLine();
                str2 += s;
            }
        }

        String[] str1s = str1.split("\\s+");
        TreeMap<Integer, String> map = new TreeMap<>();
        for (String str : str1s) {
            map.put(strToInt(str), str);
        }
        String[] str2s = str2.split("\\s+");
        for (String str : str2s) {
            map.put(strToInt(str), str);
        }
        Set<Integer> integers = map.keySet();
        StringBuilder sb = new StringBuilder();
        for (Integer integer : integers) {
            sb.append(map.get(integer)).append(" ");
        }
        System.out.println("[" + sb.deleteCharAt(sb.length() - 1).toString() + "]");
    }
}
