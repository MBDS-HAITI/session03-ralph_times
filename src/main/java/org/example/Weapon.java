package org.example;

public class Weapon {
    private String name;
    private int power;
    private String description;

    // Definition of Constructor
    public Weapon (String name, int power, String description) {
        this.name = name;
        this.power = power;
        this.description = description;
    }

    // Definition of Accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

