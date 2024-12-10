package com.tharv.milk.enumeration;

public enum Shift {
    MORNING("Morning"),
    EVENING("Evening");

    private final String value;

    Shift(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
