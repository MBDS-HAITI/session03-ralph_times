package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameTest {
    private static final List<String> usedNames = new ArrayList<>();

    public static void main(String[] args) {
        final List<String> usedNames;
        Scanner scanner = new Scanner(System.in);
        // Creation of the players
        Player player1 = createPlayer(scanner);
        Player player2 = createPlayer(scanner);

        // Display both teams
        displayTeams(player1, player2);


        int turn = 0;

        // Main game loop: continues until one team has no alive characters
        do {
            turn++;
            System.out.println("\n== Turn " + turn + " ==");

            // Execute a turn for player 1
            executeTurn(player1, player2, scanner);

            // Check if player 2 is still alive before their turn
            if (player2.countAliveCharacters() == 0) break;

            // Execute a turn for player 2 (same logic, reversed roles)
            executeTurn(player2, player1, scanner);

        } while (player1.countAliveCharacters() > 0 && player2.countAliveCharacters() > 0);

        // End of game
        System.out.println("\n== Game Over ==");
        String winner = (player1.countAliveCharacters() > 0) ? player1.getName() : player2.getName();
        System.out.println("The winner is " + winner + "!");
        displayTeams(player1, player2);

        scanner.close();


    }

    /**
     * Executes a single player's turn.
     * Handles character selection, action choice, and target selection.
     */
    private static void executeTurn(Player active, Player opponent, Scanner scanner) {
        System.out.println("\n" + active.getName() + ", choose a character to act (attack or heal):");
        active.displayAliveCharacters();

        int choiceChar = getValidChoice(scanner, 1, active.countAliveCharacters());
        Character chosen = active.getCharacter(active.getIndexAliveCharacter()[choiceChar - 1]);

        if (chosen.type.equals("Magus")) {
            int actionType = active.chooseActionForMagus(scanner);
            if (actionType == 2 || active.countAliveCharacters() == 1) {
                // Attack
                System.out.println(active.getName() + " chose an opponent to attack:");
                opponent.displayAliveCharacters();
                int targetChoice = getValidChoice(scanner, 1, opponent.countAliveCharacters());
                chosen.action(opponent.getCharacter(opponent.getIndexAliveCharacter()[targetChoice - 1]));
            } else {
                // Heal
                System.out.println(active.getName() + " chose an ally to heal:");
                active.displayAliveAlliesMagus();
                int targetChoice = getValidChoice(scanner, 1, active.countAliveCharacters() - 1);
                if ((choiceChar == 2 && active.countAliveCharacters() == 2) || (choiceChar == 3 && active.countAliveCharacters() == 3) || (choiceChar == 2 && active.countAliveCharacters() == 3 && targetChoice == 1))
                    ((Magus) chosen).action(active.getCharacter(active.getIndexAliveCharacter()[targetChoice - 1]), 1);
                else ((Magus) chosen).action(active.getCharacter(active.getIndexAliveCharacter()[targetChoice]), 1);

            }
        } else {
            // Non-Magus: can only attack
            System.out.println(active.getName() + " chose an opponent to attack:");
            opponent.displayAliveCharacters();
            int targetChoice = getValidChoice(scanner, 1, opponent.countAliveCharacters());
            chosen.action(opponent.getCharacter(opponent.getIndexAliveCharacter()[targetChoice - 1]));
        }

        displayTeams(active, opponent);
    }


    private static Player createPlayer(Scanner scanner) {

        // === Player name selection ===
        String playerName;
        do {
            System.out.print("Enter the player's name: ");
            playerName = scanner.nextLine();

            if (usedNames.contains(playerName)) {
                System.out.println("This name is already used. Please choose another one.");
            }
        } while (usedNames.contains(playerName));

        usedNames.add(playerName);
        Player player = new Player(playerName);

        System.out.println("\nHello " + playerName + "! Let's create your team of 3 characters.\n");

        // This list ensures that each team contains unique character types
        List<String> usedTypesInTeam = new ArrayList<>();

        // === Create 3 characters ===
        for (int i = 0; i < 3; i++) {
            System.out.println("=== Character " + (i + 1) + " ===");

            // === Character name selection ===
            String charName;
            do {
                System.out.print("Enter the character's name: ");
                charName = scanner.nextLine();

                if (usedNames.contains(charName)) {
                    System.out.println("This name is already used by another character or player. Please choose a different one.");
                }
            } while (usedNames.contains(charName));

            usedNames.add(charName);

            // === Character type selection ===
            int choice;
            String selectedType = null;

            do {
                System.out.println("Choose a type for " + charName + ":");
                System.out.println("1. Warrior");
                System.out.println("2. Magus");
                System.out.println("3. Colossus");
                System.out.println("4. Dwarf");

                // Use getValidChoice instead of scanner.nextInt()
                choice = getValidChoice(scanner, 1, 4);

                selectedType = switch (choice) {
                    case 1 -> "Warrior";
                    case 2 -> "Magus";
                    case 3 -> "Colossus";
                    case 4 -> "Dwarf";
                    default -> null; // Will never happen because getValidChoice already validates
                };

                if (usedTypesInTeam.contains(selectedType)) {
                    System.out.println("This type is already used in your team. Please choose another type.");
                    selectedType = null;
                }

            } while (selectedType == null);

            usedTypesInTeam.add(selectedType);

            // === Create the character instance ===
            Character newCharacter = switch (choice) {
                case 1 -> new Warrior(charName);
                case 2 -> new Magus(charName);
                case 3 -> new Colossus(charName);
                case 4 -> new Dwarf(charName);
                default -> throw new RuntimeException("Unexpected type value.");
            };

            player.addCharacter(newCharacter);
        }

        System.out.println("\nTeam creation complete for " + playerName + "!");
        player.showTeam();

        return player;
    }

    /**
     * Asks the user for a valid integer choice between min and max.
     */
    public static int getValidChoice(Scanner scanner, int min, int max) {
        int choice;
        do {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

        } while (choice < min || choice > max);

        return choice;
    }

    private static void displayTeams(Player p1, Player p2) {
        p1.showTeam();
        p2.showTeam();
    }
}




