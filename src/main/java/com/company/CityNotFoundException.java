package com.company;

public class CityNotFoundException extends Exception {
    int id;

    public CityNotFoundException(String x) {
        super(x);
    }

    public String toString() {
        return "CityNotFoundException[" + id + "]";
    }
}