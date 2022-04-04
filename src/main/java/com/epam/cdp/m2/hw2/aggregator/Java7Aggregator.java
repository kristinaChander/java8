package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> listOfPairs = new ArrayList<>();
        Map<String, Long> mapForCounting = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (mapForCounting.containsKey(word)) {
                mapForCounting.put(word, mapForCounting.get(word) + 1L);
            } else {
                mapForCounting.put(word, 1L);
            }
        }
        for (Map.Entry<String, Long> entry : mapForCounting.entrySet()) {
            listOfPairs.add(new Pair(entry.getKey(), entry.getValue()));
        }
        Collections.sort(listOfPairs, new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                long valueDifference = (o2.getValue() - o1.getValue());
                if (valueDifference != 0L) {
                    return (int) valueDifference;
                }
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        List<Pair<String, Long>> listOfPairs2 = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, listOfPairs.size()); i++) {
            listOfPairs2.add(listOfPairs.get(i));
        }

        return listOfPairs2;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> listOfRepeatedWords = new ArrayList<>();
        Map<String, Long> mapForCounting = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).toUpperCase();
            if (mapForCounting.containsKey(word)) {
                mapForCounting.put(word, mapForCounting.get(word) + 1L);
            } else {
                mapForCounting.put(word, 1L);
            }
        }
        for (Map.Entry<String, Long> entry : mapForCounting.entrySet()) {
            if(entry.getValue()>=2){
                listOfRepeatedWords.add(entry.getKey());
            }
        }
        Collections.sort(listOfRepeatedWords, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                long valueDifference = (o1.length() - o2.length());
                if (valueDifference != 0L) {
                    return (int) valueDifference;
                }
                return o1.compareTo(o2);
            }
        });
        List<String> listToConsole = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, listOfRepeatedWords.size()); i++) {
            listToConsole.add(listOfRepeatedWords.get(i));
        }
        return listToConsole;
    }
}
