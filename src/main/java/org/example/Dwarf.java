package org.example;

public class Dwarf extends Character implements Attacker {

    // Definition of Constructor
    public Dwarf(String name) {
        super(name, 80, new Weapon("Battle Axe", 40, "Balanced and reliable battle axe"), "Dwarf");
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


