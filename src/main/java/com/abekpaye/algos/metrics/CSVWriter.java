package com.abekpaye.algos.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final String file;
    private final StringBuilder sb = new StringBuilder();

    public CSVWriter(String file) {
        this.file = file;
    }

    public void addLine(String... values) {
        sb.append(String.join(",", values)).append("\n");
    }

    public void write() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

