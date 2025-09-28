package com.abekpaye.algos.sort;

import com.abekpaye.algos.metrics.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics m) {
        m.startTimer();
        quickSort(arr, 0, arr.length - 1, m);
        m.stopTimer();
    }

    private static void quickSort(int[] arr, int lo, int hi, Metrics m) {
        while (lo < hi) {
            m.enterRecursion();
            int p = partition(arr, lo, hi, m);
            // Рекурсируем только в меньший подмассив
            if (p - lo < hi - p) {
                quickSort(arr, lo, p - 1, m);
                lo = p + 1; // итерация для правого
            } else {
                quickSort(arr, p + 1, hi, m);
                hi = p - 1; // итерация для левого
            }
            m.exitRecursion();
        }
    }

    private static int partition(int[] arr, int lo, int hi, Metrics m) {
        int pivotIndex = lo + rand.nextInt(hi - lo + 1);
        swap(arr, pivotIndex, hi);
        int pivot = arr[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            m.incComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, hi);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
