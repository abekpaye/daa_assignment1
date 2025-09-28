package com.abekpaye.algos.sort;

import com.abekpaye.algos.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 20; t++) {
            int[] arr = rand.ints(100, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics m = new Metrics();
            MergeSort.sort(arr, m);

            assertArrayEquals(expected, arr);
            assertTrue(m.getMaxRecursionDepth() > 0);
        }
    }

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }
}
