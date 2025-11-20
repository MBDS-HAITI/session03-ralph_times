package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Player class represents a player who has exactly 3 characters in their team.
 */
public class Player {
    private String name;
    private Character[] team = new Character[3]; // Fixed-size team of 3 characters
    private int count = 0; // Number of characters currently added

    /**
     * Constructor - creates a new player with an empty team.
     *
     * @param name The player's name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Adds a new character to the team if there is still an empty slot.
     *
     * @param character The character to add
     */
    public void addCharacter(Character character) {
        if (count < 3) {
            team[count] = character;
            count++;
            System.out.println(character.name + " added to " + name + "'s team.");
        } else {
            System.out.println("Team is already full! (3 characters maximum)");
        }
    }

    /**
     * Displays all characters in the player's team.
     */
    public void showTeam() {
        System.out.println("\n" + name + "'s Team:");
        for (int i = 0; i < count; i++) {
            team[i].showStatus();
        }
    }

    /**
     * Checks if the player still has at least one alive character.
     *
     * @return the quantity n of alive characters, 0 means no alive character
     */
    public int countAliveCharacters() {
        int n = 0;
        for (int i = 0; i < count; i++) {
            if (team[i].isAlive()) {
                n++;
            }
        }
        return n;
    }

    /**
     * Returns a table of index of alive characters.
     *
     */
    public int[] getIndexAliveCharacter() {
        int[] arr = new int[count];
        int j = 0;
        for (int i = 0; i < count; i++) {
            if (team[i].isAlive()) {
                arr[j] = i;
                j++;
            }
        }
        return arr;
    }

    /**
     * Returns a  at a specific index.
     *
     * @param index The index (0–2)
     * @return The corresponding character
     */
    public Character getCharacter(int index) {
        if (index >= 0 && index < count) {
            return team[index];
        }
        return null;
    }

    /**
     * Displays all alive characters of a player.
     */
    public void displayAliveCharacters() {
        for (int i = 1; i <= this.countAliveCharacters(); i++) {
            Character c = this.getCharacter(this.getIndexAliveCharacter()[i - 1]);
            System.out.println(i + ". " + c.name + " (" + c.type + ")");
        }
    }

    /**
     * Displays all alive allies of a character Magus' type.
     */
    public void displayAliveAlliesMagus() {
        int j = 0;
        for (int i = 1; i <= this.countAliveCharacters(); i++) {
            j++;

            //if it is Magus, skip it and continue
            if (team[i - 1].type.equals("Magus")) {

                if (j == this.countAliveCharacters()) break; // prevent going out of bounds
                j++;
            }

            // if j exceeds the array size → stop
            if (j > this.countAliveCharacters()) break;
            Character c = this.getCharacter(this.getIndexAliveCharacter()[j - 1]);
            System.out.println(i + ". " + c.name + " (" + c.type + ")");
        }
    }

    public int chooseActionForMagus(Scanner scanner) {
        if (this.countAliveCharacters() == 1) return 2; // no ally to heal
        System.out.println("\nChoose an action:");
        System.out.println("1. Heal an ally");
        System.out.println("2. Attack an opponent");
        return GameTest.getValidChoice(scanner, 1, 2);
    }


    public String getName() {
        return name;
    }

}