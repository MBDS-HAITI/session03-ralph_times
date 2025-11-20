package org.example;

public abstract class Character {
    protected String name;
    protected int healthPoints;
    protected String type;
    protected Weapon armor;

    // Definition of Constructor
    public Character(String name, int healthPoints, Weapon armor, String type) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.type = type;
        this.armor = armor;
    }

    // Definition of Accessors


    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String getType() {
        return type;
    }

    public Weapon getArmor() {
        return armor;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void showStatus() {
        System.out.println(name + " | HP: " + healthPoints + " ( " + type + " )");
    }

    public abstract void action(Character target);
}
