package org.example;
import java.util.ArrayList;
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
     * @return True if at least one character is alive, false otherwise
     */
    public boolean hasAliveCharacters() {
        for (int i = 0; i < count; i++) {
            if (team[i].isAlive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a character at a specific index.
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

    public String getName() {
        return name;
    }

}