package org.example;

public class Warrior extends Character implements Attacker {

    // Definition of Constructor
    public Warrior(String name) {

        super(name, 100, new Weapon("Sword", 30, "Balanced and reliable blade"), "Warrior");
    }


    @Override
    public void attack(Character target) {
        System.out.println(super.name + " swings his " + armor.getName() + " at " + target.name + "!");
        target.healthPoints = target.healthPoints - armor.getPower();
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
