package com.main;

import java.util.Map;

public class Info {

    public static void getResultInfo(Map<Character, Integer> list){

        System.out.println("Частоты: ");
        for (Map.Entry<Character, Integer> entry : list.entrySet()) {
            String form = Calculating.getRightCounterForm(entry.getValue());
            System.out.println(entry.getKey() + " - " + entry.getValue() + form);
        }
        System.out.println(Calculating.getPeriodicityResult(list));
    }
}
