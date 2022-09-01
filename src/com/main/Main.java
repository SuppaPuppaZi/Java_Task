package com.main;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Calculating calc = new Calculating();
        DownloadService ds = new DownloadService();
        Info info = new Info();

        int number = calc.getRandomNumber();
        String url = "http://numbersapi.com/" + number + "/trivia";

        String fact = ds.NumberFact(url);
        fact = fact.replaceAll("\\s+", "");

        Map<Character,Integer> list = new HashMap<>();

        calc.countCharacters(list, fact);

        info.getResultInfo(list);
    }


}





