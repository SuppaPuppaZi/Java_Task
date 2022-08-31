import java.util.Map;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        int number = (int) Math.round(Math.random() * 1000);
        String url = "http://numbersapi.com/" + number + "/trivia";

        String fact = NumberFact(url);
        System.out.println(fact);
        fact = fact.replaceAll("\\s+", "");

        Map<Character, Integer> list = new HashMap<>();

        countCharacters(list, fact);

        getResultInfo(list);
    }

    public static String NumberFact(String url){
        HttpURLConnection connection = null;

        try{
            connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

                String readyQuery = sb.toString();
                return readyQuery.substring(0, readyQuery.length() - 1);

            } else {
                return "fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage();
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return "";
    }


    public static void countCharacters(Map<Character,Integer> list,String fact){
        int counter = 0;

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

    public  static void getResultInfo(Map<Character, Integer> list){

        System.out.println("Частоты: ");
        for (Map.Entry<Character, Integer> entry : list.entrySet()) {
            String form = getRightCounterForm(entry.getValue());
            System.out.println(entry.getKey() + " - " + entry.getValue() + form);

        }

        System.out.println(getPeriodicityInfo(list));
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


    public static float getSum(Map<Character,Integer> list){

        float sum = 0;

        for(Integer value : list.values()) {
            sum += value;
        }

        return sum;
    }

    public static String getPeriodicityInfo(Map<Character,Integer> list){

        float periodicity = getSum(list) / list.size();

        String resultInfo = "Среднее значение частоты " + Math.round(getSum(list)) +
                "/" + list.size() + " = " + periodicity + "\nСимволы, которые соответствуют условию " +
                "наиболее близкого значения частоты к среднему значанию: ";

        for (Map.Entry<Character, Integer> entry : list.entrySet()){
            if(entry.getValue() == Math.round(periodicity)){
                resultInfo += entry.getKey() + "(" + (int) + entry.getKey() + ")" + ",";
            }
        }

        return resultInfo.substring(0, resultInfo.length() - 1);


    }



}





