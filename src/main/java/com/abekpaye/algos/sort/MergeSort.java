package com.abekpaye.algos.sort;

import com.abekpaye.algos.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics m) {
        int[] aux = new int[arr.length];
        m.startTimer();
        sort(arr, aux, 0, arr.length - 1, m);
        m.stopTimer();
    }

    private static void sort(int[] arr, int[] aux, int lo, int hi, Metrics m) {
        if (hi - lo <= CUTOFF) {
            insertionSort(arr, lo, hi, m);
            return;
        }
        m.enterRecursion();
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid, m);
        sort(arr, aux, mid + 1, hi, m);
        merge(arr, aux, lo, mid, hi, m);
        m.exitRecursion();
    }

    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi, Metrics m) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        for (int k = lo, i = lo, j = mid + 1; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) { m.incComparisons(); arr[k] = aux[j++]; }
            else { m.incComparisons(); arr[k] = aux[i++]; }
        }
    }

    private static void insertionSort(int[] arr, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo && arr[j] > key) {
                m.incComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
