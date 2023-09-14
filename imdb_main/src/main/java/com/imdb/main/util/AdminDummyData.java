package com.imdb.main.util;

public enum AdminDummyData {
    ADMIN_USERNAME("admin_username"),

    ADMIN_PASSWORD("admin_password");
    private final String value;

     AdminDummyData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
