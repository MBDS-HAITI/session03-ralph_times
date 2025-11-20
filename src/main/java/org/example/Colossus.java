package org.example;

public class Colossus extends Character implements Attacker {

    // Definition of Constructor
    public Colossus(String name) {
        super(name, 150, new Weapon("Hammer", 20, "Balanced and reliable hammer"), "Colossus");
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
}

