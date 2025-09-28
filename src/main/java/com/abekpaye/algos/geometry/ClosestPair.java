package com.abekpaye.algos.geometry;

import com.abekpaye.algos.metrics.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] points, Metrics m) {
        m.startTimer();
        Point[] sortedX = points.clone();
        Arrays.sort(sortedX, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[points.length];
        double res = closestRec(sortedX, aux, 0, points.length - 1, m);
        m.stopTimer();
        return res;
    }

    private static double closestRec(Point[] pts, Point[] aux, int lo, int hi, Metrics m) {
        if (hi - lo <= 3) {
            return bruteForce(pts, lo, hi, m);
        }
        m.enterRecursion();
        int mid = (lo + hi) / 2;
        double midX = pts[mid].x;
        double d1 = closestRec(pts, aux, lo, mid, m);
        double d2 = closestRec(pts, aux, mid + 1, hi, m);
        double d = Math.min(d1, d2);

        int stripCount = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                aux[stripCount++] = pts[i];
            }
        }
        Arrays.sort(aux, 0, stripCount, Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < stripCount; i++) {
            for (int j = i + 1; j < stripCount && (aux[j].y - aux[i].y) < d; j++) {
                m.incComparisons();
                d = Math.min(d, dist(aux[i], aux[j]));
            }
        }
        m.exitRecursion();
        return d;
    }

    private static double bruteForce(Point[] pts, int lo, int hi, Metrics m) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                m.incComparisons();
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        return min;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
