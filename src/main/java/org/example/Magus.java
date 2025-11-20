package org.example;

public class Magus extends Character implements Healer, Attacker {

    // Definition of Attribute

    // Definition of Constructor
    public Magus(String name) {
        super(name, 120, new Weapon("Magic Staff", 10, "Emits healing energy"), "Magus");
    }

    @Override
    public void heal(Character ally) {
        final int healAmount = 40;
        System.out.println(name + " heals " + ally.name + "!");
        ally.healthPoints += healAmount;
        System.out.println(ally.name + " recovers " + healAmount + " HP!");
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " swings his " + armor.getName() + " at " + target.name + "!");
        target.healthPoints -= armor.getPower();
    }
    @Override
    public void action(Character target) {
        if (target != null && target.isAlive()) {
            attack(target);
        } else {
            System.out.println("No valid target to attack!");
        }
    }

    public void action(Character target, int i) {
        if (i == 1) {
            heal(target);
        } else {
            attack(target);
        }
    }
}
