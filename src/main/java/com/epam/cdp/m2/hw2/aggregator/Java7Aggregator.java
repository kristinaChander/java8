package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        Pair<String, Long> pair;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            Long wordsCount = 0L;
            for (String item : words) {
                if (item.equalsIgnoreCase(word)) {
                    wordsCount++;
                }
            }
            pair = new Pair(word, wordsCount);
            if (!listOfPairs.contains(pair)) {
                listOfPairs.add(pair);
            }
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
        List<Pair<String, Long>> listOfPairs = new ArrayList<>();
        Pair<String, Long> pair;
        List<String> repeatedWords = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            Long wordsCount = 0L;
            for (String item : words) {
                if (item.equalsIgnoreCase(word)) {
                    wordsCount++;
                }
                if (wordsCount >= 2) {
                    pair = new Pair(word.toUpperCase(), wordsCount);
                    if (!listOfPairs.contains(pair)) {
                        listOfPairs.add(pair);
                    }
                }
            }
            repeatedWords = new ArrayList<>();
            for (Pair<String, Long> entry : listOfPairs) {
                repeatedWords.add(entry.getKey());
            }
        }
        Collections.sort(repeatedWords, new Comparator<String>() {
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
        for (int i = 0; i < Math.min(limit, repeatedWords.size()); i++) {
            listToConsole.add(repeatedWords.get(i));
        }
        return listToConsole;
    }
}
