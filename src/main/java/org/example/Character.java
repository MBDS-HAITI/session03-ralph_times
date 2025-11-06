package org.example;

public abstract class Character {
    protected String name;
    protected int healthPoints;
    protected Weapon armor;

    // Definition of Constructor
    public Character(String name, int healthPoints, Weapon armor) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.armor = armor;
    }

    // Definition of Accessors


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Weapon getArmor() {
        return armor;
    }

    public void setArmor(Weapon armor) {
        this.armor = armor;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void showStatus() {
        System.out.println(name + " | HP: " + healthPoints + " | Weapon: " + armor);
    }

    public abstract void action(Character target);
}
