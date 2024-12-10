package com.tharv.milk.enumeration;

public enum MilkType {
    COW("Cow"),
    BUFFALO("Buffalo");

    private final String value;

    MilkType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
