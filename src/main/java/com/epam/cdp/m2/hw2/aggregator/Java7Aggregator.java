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
        for (int i = 0; i < words.size() - 1; i++) {
            String word = words.get(i);
            limit = 0;
            for (String item : words) {
                if (item.equalsIgnoreCase(word)) {
                    limit++;
                }
            }
            pair = new Pair(word, limit);
            if (!listOfPairs.contains(pair)) {
                listOfPairs.add(pair);
            }
        }
        Collections.sort(listOfPairs, new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        Collections.sort(listOfPairs, new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return listOfPairs;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}
