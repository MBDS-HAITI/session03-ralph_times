package org.example;

public class Magus extends Character implements Healer {

    // Definition of Attribute

    // Definition of Constructor
    public Magus(String name) {
        super(name, 120, new Weapon("Magic Staff", 10, "Emits healing energy"));
    }

    @Override
    public void heal(Character ally) {
        final int healAmount = 40;
        System.out.println(name + " heals " + ally.name + "!");
        ally.healthPoints = ally.healthPoints + healAmount;
        System.out.println(ally.name + " recovers " + healAmount + " HP!");
    }

    @Override
    public void action(Character target) {
        if (target != null && target.isAlive()) {
            heal(target);
        } else {
            System.out.println("No valid ally to heal!");
        }
    }
}
