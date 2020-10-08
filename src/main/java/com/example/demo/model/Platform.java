package com.example.demo.model;

public enum Platform {
    PS4("PS4"),
    XBOX_ONE("XBOX ONE"),
    PS5 ("PS5"),
    XBOX_SERIES_X ("XBOX SERIES X");

    private String model;

    Platform(String s) {
        setModel(s);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
