package com.abekpaye.algos.select;

import com.abekpaye.algos.metrics.Metrics;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics m) {
        m.startTimer();
        int res = select(arr, 0, arr.length - 1, k, m);
        m.stopTimer();
        return res;
    }

    private static int select(int[] arr, int lo, int hi, int k, Metrics m) {
        while (true) {
            if (lo == hi) return arr[lo];
            int pivot = medianOfMedians(arr, lo, hi, m);
            int pivotIndex = partition(arr, lo, hi, pivot, m);

            if (k == pivotIndex) return arr[k];
            else if (k < pivotIndex) {
                hi = pivotIndex - 1;
            } else {
                lo = pivotIndex + 1;
            }
        }
    }

    private static int medianOfMedians(int[] arr, int lo, int hi, Metrics m) {
        int n = hi - lo + 1;
        if (n <= 5) {
            Arrays.sort(arr, lo, hi + 1);
            return arr[lo + n / 2];
        }
        int groups = (n + 4) / 5;
        for (int i = 0; i < groups; i++) {
            int gLo = lo + i * 5;
            int gHi = Math.min(gLo + 4, hi);
            Arrays.sort(arr, gLo, gHi + 1);
            int median = arr[gLo + (gHi - gLo) / 2];
            swap(arr, lo + i, gLo + (gHi - gLo) / 2);
            m.incComparisons();
        }
        return medianOfMedians(arr, lo, lo + groups - 1, m);
    }

    private static int partition(int[] arr, int lo, int hi, int pivot, Metrics m) {
        int pivotIndex = lo;
        for (int i = lo; i <= hi; i++) {
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            m.incComparisons();
            if (arr[i] < pivot) {
                swap(arr, store++, i);
            }
        }
        swap(arr, store, hi);
        return store;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
