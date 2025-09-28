package com.abekpaye.algos.sort;

import com.abekpaye.algos.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 20; t++) {
            int[] arr = rand.ints(100, -1000, 1000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics m = new Metrics();
            QuickSort.sort(arr, m);

            assertArrayEquals(expected, arr);

            // глубина рекурсии ≲ 2*log2(n) + O(1)
            int bound = (int) (2 * Math.floor(Math.log(arr.length) / Math.log(2)) + 5);
            assertTrue(m.getMaxRecursionDepth() <= bound, "Depth too high");
        }
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }
}
