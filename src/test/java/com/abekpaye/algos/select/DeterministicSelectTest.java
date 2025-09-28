package com.abekpaye.algos.select;

import com.abekpaye.algos.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSelectMatchesSorted() {
        Random rand = new Random();
        for (int t = 0; t < 50; t++) {
            int[] arr = rand.ints(200, -1000, 1000).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rand.nextInt(arr.length);

            Metrics m = new Metrics();
            int result = DeterministicSelect.select(arr.clone(), k, m);

            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testEdgeCases() {
        int[] arr = {7};
        Metrics m = new Metrics();
        assertEquals(7, DeterministicSelect.select(arr, 0, m));

        int[] arr2 = {5, 1};
        assertEquals(1, DeterministicSelect.select(arr2.clone(), 0, new Metrics()));
        assertEquals(5, DeterministicSelect.select(arr2.clone(), 1, new Metrics()));
    }
}
