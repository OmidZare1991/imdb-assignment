package com.imdb.main.service;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractItemReader<T> implements ItemStreamReader<T> {
    protected BufferedReader reader;
    protected void openReader(String resourcePath) throws ItemStreamException {
        Resource resource = getResourceLoader().getResource(resourcePath);
        try {
            reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            reader.readLine(); // skipping the header
        } catch (IOException e) {
            throw new ItemStreamException("Error opening the file", e);
        }
    }

    protected boolean isMaxRecordsReached() {
        return getRecordsRead() >= getMaxRecords();
    }

    protected boolean isEndOfFile() throws IOException {
        return reader != null && reader.readLine() == null;
    }

    protected void closeReader() throws ItemStreamException {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            throw new ItemStreamException("Error closing the file", e);
        }
    }

    protected abstract T processTokens(String[] tokens) throws Exception;

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        openReader(getResourcePath());
    }

    @Override
    public T read() throws Exception {
        T item = readAndProcessLine();
        if (item == null) {
            closeReader();
        }
//        recordsRead++;
        return item;
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // No need to update anything in this reader
    }

    @Override
    public void close() throws ItemStreamException {
        closeReader();
    }

    protected abstract String getResourcePath();

    protected T readAndProcessLine() throws Exception {
        if (isMaxRecordsReached() || isEndOfFile()) {
            return null;
        }

        String line = reader.readLine();
        if (line == null) {
            return null;
        }

        String[] tokens = line.split("\t");
        return processTokens(tokens);
    }

    protected abstract int getMaxRecords();

    protected abstract ResourceLoader getResourceLoader();
    protected abstract int getRecordsRead();
}