package com.abekpaye.algos.metrics;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private long recursionDepth = 0;
    private long maxRecursionDepth = 0;
    private long startTime;
    private long endTime;

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }

    public void incComparisons() { comparisons++; }
    public void incAllocations() { allocations++; }

    public void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }
    }

    public void exitRecursion() {
        recursionDepth--;
    }

    public long getComparisons() { return comparisons; }
    public long getAllocations() { return allocations; }
    public long getMaxRecursionDepth() { return maxRecursionDepth; }
}
