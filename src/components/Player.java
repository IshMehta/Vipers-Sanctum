package components;

import java.util.ArrayList;

public class Player {
    private int playerHP;
    //knife count, maul count, sword count, bow count,
    //attack pot count, health pot count, lucky pot count
    private ArrayList<Integer> inventoryCount;
    private String selectedWeapon;

    public Player(int hp, String weapon) {
        playerHP = hp;
        selectedWeapon = weapon;
        inventoryCount = new ArrayList<>(0);
        inventoryCount.add(0);
        inventoryCount.add(0);
        inventoryCount.add(0);
        inventoryCount.add(0);
        inventoryCount.add(0);
        inventoryCount.add(0);
        inventoryCount.add(0);
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public ArrayList<Integer> getInventoryCount() {
        return inventoryCount;
    }

    public void addElement(String element) {
        switch (element) {
        case "Knife":
            inventoryCount.set(0, inventoryCount.get(0) + 1);
            break;
        case "Maul":
            inventoryCount.set(1, inventoryCount.get(1) + 1);
            break;
        case "Sword":
            inventoryCount.set(2, inventoryCount.get(2) + 1);
            break;
        case "Bow":
            inventoryCount.set(3, inventoryCount.get(3) + 1);
            break;
        case "Attack":
            inventoryCount.set(4, inventoryCount.get(4) + 1);
            break;
        case "Health":
            inventoryCount.set(5, inventoryCount.get(5) + 1);
            break;
        case "Lucky":
            inventoryCount.set(6, inventoryCount.get(6) + 1);
            break;
        default:
            break;
        }
    }

    public void removeElement(String element) {
        switch (element) {
        case "Knife":
            inventoryCount.set(0, inventoryCount.get(0) - 1);
            break;
        case "Maul":
            inventoryCount.set(1, inventoryCount.get(1) - 1);
            break;
        case "Sword":
            inventoryCount.set(2, inventoryCount.get(2) - 1);
            break;
        case "Bow":
            inventoryCount.set(3, inventoryCount.get(3) - 1);
            break;
        case "Attack":
            inventoryCount.set(4, inventoryCount.get(4) - 1);
            break;
        case "Health":
            inventoryCount.set(5, inventoryCount.get(5) - 1);
            break;
        case "Lucky":
            inventoryCount.set(6, inventoryCount.get(6) - 1);
            break;
        default:
            break;
        }
    }

    public String getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setSelectedWeapon(String selectedWeapon) {
        this.selectedWeapon = selectedWeapon;
    }
}
