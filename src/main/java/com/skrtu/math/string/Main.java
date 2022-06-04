package com.skrtu.math.string;

public class Main {


    public static void main(String[] args) {
        String str = "adadc";
        String winStr = "";
        String resultStr = "";
        for (int i = 0; i < str.length(); i++) {
            String tmp = String.valueOf(str.charAt(i));
            if (winStr.contains(tmp)) {
                winStr = winStr.substring(winStr.indexOf(tmp)) + tmp;
            } else {
                winStr = winStr + tmp;
            }
            if (resultStr.length() < winStr.length()) {
                resultStr = winStr;
            }
        }
    }
}
