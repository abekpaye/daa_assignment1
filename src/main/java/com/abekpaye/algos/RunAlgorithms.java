package com.abekpaye.algos;

import com.abekpaye.algos.geometry.ClosestPair;
import com.abekpaye.algos.metrics.CSVWriter;
import com.abekpaye.algos.metrics.Metrics;
import com.abekpaye.algos.select.DeterministicSelect;
import com.abekpaye.algos.sort.MergeSort;
import com.abekpaye.algos.sort.QuickSort;

import java.util.Random;

public class RunAlgorithms {

    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 10000};
        CSVWriter csv = new CSVWriter("metrics.csv");
        csv.addLine("Algorithm,Size,Time(ns),MaxRecursionDepth,Comparisons,Allocations");

        Random rand = new Random();

        for (int n : sizes) {
            int[] arr = rand.ints(n, 0, 10000).toArray();

            // MergeSort
            Metrics m = new Metrics();
            MergeSort.sort(arr.clone(), m);
            csv.addLine("MergeSort", String.valueOf(n),
                    String.valueOf(m.getElapsedTime()),
                    String.valueOf(m.getMaxRecursionDepth()),
                    String.valueOf(m.getComparisons()),
                    String.valueOf(m.getAllocations()));

            // QuickSort
            m = new Metrics();
            QuickSort.sort(arr.clone(), m);
            csv.addLine("QuickSort", String.valueOf(n),
                    String.valueOf(m.getElapsedTime()),
                    String.valueOf(m.getMaxRecursionDepth()),
                    String.valueOf(m.getComparisons()),
                    String.valueOf(m.getAllocations()));

            // DeterministicSelect (select median)
            m = new Metrics();
            int k = n / 2;
            DeterministicSelect.select(arr.clone(), k, m);
            csv.addLine("DeterministicSelect", String.valueOf(n),
                    String.valueOf(m.getElapsedTime()),
                    String.valueOf(m.getMaxRecursionDepth()),
                    String.valueOf(m.getComparisons()),
                    String.valueOf(m.getAllocations()));

            // ClosestPair (random points)
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPair.Point(rand.nextDouble() * 10000, rand.nextDouble() * 10000);
            }
            m = new Metrics();
            ClosestPair.closestPair(pts, m);
            csv.addLine("ClosestPair", String.valueOf(n),
                    String.valueOf(m.getElapsedTime()),
                    String.valueOf(m.getMaxRecursionDepth()),
                    String.valueOf(m.getComparisons()),
                    String.valueOf(m.getAllocations()));
        }

        csv.write();
        System.out.println("Metrics saved to metrics.csv");
    }
}
