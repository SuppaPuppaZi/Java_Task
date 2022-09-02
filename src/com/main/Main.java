package com.main;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        int number = Calculating.getRandomNumber();
        String url = "http://numbersapi.com/" + number + "/trivia";

        String fact = DownloadService.NumberFact(url);
        fact = fact.replaceAll("\\s+", "");

        Map<Character,Integer> list = new HashMap<>();

        Calculating.countCharacters(list, fact);
        Info.getResultInfo(list);

    }


}





