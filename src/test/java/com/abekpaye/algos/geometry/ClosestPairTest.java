package com.abekpaye.algos.geometry;

import com.abekpaye.algos.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testSmallCasesAgainstBruteForce() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(1,0),
                new ClosestPair.Point(5,5),
                new ClosestPair.Point(2,1)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.closestPair(pts, m);

        double brute = bruteForce(pts);
        assertEquals(brute, d, 1e-9);
    }

    @Test
    void testRandomPoints() {
        Random rand = new Random();
        for (int t = 0; t < 10; t++) {
            ClosestPair.Point[] pts = new ClosestPair.Point[200];
            for (int i = 0; i < pts.length; i++) {
                pts[i] = new ClosestPair.Point(rand.nextDouble()*1000, rand.nextDouble()*1000);
            }
            Metrics m = new Metrics();
            double d1 = ClosestPair.closestPair(pts, m);
            double d2 = bruteForce(pts);
            assertEquals(d2, d1, 1e-9);
        }
    }

    private double bruteForce(ClosestPair.Point[] pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i+1; j < pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                double dist = Math.sqrt(dx*dx + dy*dy);
                min = Math.min(min, dist);
            }
        }
        return min;
    }
}
