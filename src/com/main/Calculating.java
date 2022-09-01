package com.main;

import java.util.Map;

public class Calculating {

    public static void countCharacters(Map<Character,Integer> list, String fact){

        int counter;

        for (int i = 0; i < fact.length(); i++) {
            counter = 0;
            for (int j = 0; j < fact.length(); j++) {
                if (fact.charAt(i) == fact.charAt(j)) {
                    counter++;
                }
                list.put(fact.charAt(i), counter);
            }

        }
    }


    public static float getSum(Map<Character,Integer> list){

        float sum = 0;

        for(Integer value : list.values()) {
            sum += value;
        }

        return sum;
    }


    public static float getPeriodicity(Map<Character,Integer> list){
        return getSum(list) / list.size();
    }


    public static int getRandomNumber() {
        return (int) Math.round(Math.random() * 1000);
    }


    public static String getRightCounterForm(int element) {

        int lastDigit = element % 10;
        int preLastDigits = element % 100;

        if (preLastDigits == 12 || preLastDigits == 13 || preLastDigits == 14) {
            return " раз";
        } else if (lastDigit == 2 || lastDigit == 3 || lastDigit == 4) {
            return " раза";
        } else {
            return " раз";
        }


    }


    public static String getPeriodicityResult(Map<Character,Integer> list){

        StringBuilder resultInfo = new StringBuilder("Среднее значение частоты " + Math.round(getSum(list)) +
                "/" + list.size() + " = " + getPeriodicity(list) + "\nСимволы, которые соответствуют условию " +
                "наиболее близкого значения частоты к среднему значанию: ");

        int i = 0;

        for (Map.Entry<Character, Integer> entry : list.entrySet()){
            if(entry.getValue() == Math.round(getPeriodicity(list))){
                resultInfo.append(entry.getKey()).append("(").append(+entry.getKey()).append(")").append(",");
                i++;
                if(i >= 4) break;
            }
        }

        return resultInfo.substring(0, resultInfo.length() - 1);
    }

}
