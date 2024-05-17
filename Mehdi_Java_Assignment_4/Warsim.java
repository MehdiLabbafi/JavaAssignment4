import warrior.*;
import weapon.*;
import armour.*;
import weather.*;
import utility.*;

import java.util.Scanner;
import java.util.Random;

public class Warsim {

  // Objects
  public static Scanner input = new Scanner(System.in);
  public static Random randNum = new Random();
  public static Ink ink = new Ink();
  public static Weather weather;

  // Player Objects
  public static Warrior player1, player2, player3, player4;
  public static Weapon pWeapon1, pWeapon2, pWeapon3, pWeapon4;
  public static Armour pArmour1, pArmour2, pArmour3, pArmour4;

  // Variables
  public static boolean gameOver = false;
  public static int currentTurn = 1;
  public static int choice = 0;
  public static int attackType = 0;
  public static int damage = 0;
  public static String winner = "";
  public static int winCount = 0;
  public static int lossCount = 0;

  public static void main(String[] args) {
    ink.welcomeMessage();

    // set a random weather for the battle
    int weatherType = randNum.nextInt(4) + 1;
    createWeather(weatherType);

    // Player 1 Setup
    System.out.println("Setting up Player 1");
    setupPlayer("Player 1");

    // Player 2 Setup
    System.out.println("Setting up Player 2");
    setupPlayer("Player 2");

    // Player 3 Setup
    System.out.println("Setting up Player 3");
    setupPlayer("Player 3");

    // Player 4 Setup
    System.out.println("Setting up Player 4");
    setupPlayer("Player 4");

    ink.printStats(player1, player2, player3, player4);

    // main game loop
    while (!gameOver) {
      if (currentTurn == 1) {
        executeTurn(player1, pWeapon1, pArmour1, "Player 2", player2, pArmour2);
      } else if (currentTurn == 2) {
        executeTurn(player2, pWeapon2, pArmour2, "Player 3", player3, pArmour3);
      } else if (currentTurn == 3) {
        executeTurn(player3, pWeapon3, pArmour3, "Player 4", player4, pArmour4);
      } else if (currentTurn == 4) {
        executeTurn(player4, pWeapon4, pArmour4, "Player 1", player1, pArmour1);
      }

      currentTurn = (currentTurn % 4) + 1;
    }

    ink.printGameOver(winner);
    viewBattleHistory();
  }

  public static void setupPlayer(String who) {
    ink.printWarriorMenu();
    choice = input.nextInt();
    createWarrior(who, choice);

    ink.printWeaponMenu();
    choice = input.nextInt();
    createWeapon(who, choice);

    ink.printArmourMenu();
    choice = input.nextInt();
    createArmour(who, choice);
}

public static void executeTurn(Warrior attacker, Weapon attackerWeapon, Armour attackerArmour, String targetName, Warrior target, Armour targetArmour) {
  if (gameOver) {
      System.out.println("The game is over. No further turns can be executed.");
      return;
  }

  ink.printAttackMenu();
  System.out.println("Choose Your Attack!");
  System.out.println("1) Normal: Standard attack with balanced damage and speed.");
  System.out.println("2) Heavy: Powerful attack that deals more damage but reduces dexterity for this round.");

  attackType = input.nextInt();
  damage = attackerWeapon.strike(weather.getSeverity(), attackType, attacker.getStrength(), attacker.getDexterity());
  damage = targetArmour.reduceDamage(damage);
  target.takeDamage(damage);

  if (!target.isAlive()) {
      winner = target.getWarriorType() + " was defeated by " + attacker.getWarriorType();
      gameOver = true;
      winCount++;
      System.out.println(winner);
  }

  ink.printStats(player1, player2, player3, player4);
}



  public static void createWarrior(String who, int choice) {
    switch (who) {
      case "Player 1":
        player1 = createWarriorByChoice(choice);
        break;
      case "Player 2":
        player2 = createWarriorByChoice(choice);
        break;
      case "Player 3":
        player3 = createWarriorByChoice(choice);
        break;
      case "Player 4":
        player4 = createWarriorByChoice(choice);
        break;
    }
  }

  public static Warrior createWarriorByChoice(int choice) {
    switch (choice) {
      case 1:
        return new Human("Human");
      case 2:
        return new Elf("Elf");
      case 3:
        return new Orc("Orc");
      case 4:
        return new Dwarf("Dwarf");
      default:
        System.out.println("Invalid choice!");
        return null;
    }
  }

  public static void createWeapon(String who, int choice) {
    switch (who) {
      case "Player 1":
        pWeapon1 = createWeaponByChoice(choice);
        break;
      case "Player 2":
        pWeapon2 = createWeaponByChoice(choice);
        break;
      case "Player 3":
        pWeapon3 = createWeaponByChoice(choice);
        break;
      case "Player 4":
        pWeapon4 = createWeaponByChoice(choice);
        break;
    }
  }

  public static Weapon createWeaponByChoice(int choice) {
    switch (choice) {
      case 1:
        return new Dagger();
      case 2:
        return new Sword();
      case 3:
        return new Axe();
      case 4:
        return new MagicStaff();
      default:
        System.out.println("Invalid choice!");
        return null;
    }
  }

  public static void createArmour(String who, int choice) {
    switch (who) {
      case "Player 1":
        pArmour1 = createArmourByChoice(choice);
        break;
      case "Player 2":
        pArmour2 = createArmourByChoice(choice);
        break;
      case "Player 3":
        pArmour3 = createArmourByChoice(choice);
        break;
      case "Player 4":
        pArmour4 = createArmourByChoice(choice);
        break;
    }
  }

  public static Armour createArmourByChoice(int choice) {
    switch (choice) {
      case 1:
        return new Leather();
      case 2:
        return new Chainmail();
      case 3:
        return new Platemail();
      default:
        System.out.println("Invalid choice!");
        return null;
    }
  }

  public static void viewBattleHistory() {
    System.out.println("Battle History:");
    System.out.println("Wins: " + winCount);
    System.out.println("Losses: " + lossCount);
  }

  public static void createWeather(int weatherType) {
    switch (weatherType) {
      case 1:
        weather = new Sun();
        break;
      case 2:
        weather = new Rain();
        break;
      case 3:
        weather = new Wind();
        break;
      case 4:
        weather = new Storm();
        break;
      default:
        System.out.println("Run!! Godzilla!!!");
        break;
    }
  }
}
