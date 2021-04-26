package components;

import java.util.Arrays;

public class Dungeon {
    private int[][] roomsArray;
    private boolean[] roomsAccessed;
    private boolean[] monstersDefeated;
    private String lastDirection;

    public Dungeon() {
        roomsArray = new int[5][5];
        roomsAccessed = new boolean[9];
        monstersDefeated = new boolean[9];
        randomizeRooms();
        roomsAccessed[0] = true;
        System.out.println(Arrays.deepToString(roomsArray));
    }

    private void randomizeRooms() {
        roomsArray[0][2] = 1;
        int currentX = 0;
        int currentY = 2;
        for (int i = 0; i < 8; i++) {
            int pathGen = (int) (Math.random() * 3);
            if (pathGen == 0) {
                if (currentX == 4 || roomsArray[currentX + 1][currentY] != 0) {
                    i--;
                } else {
                    currentX++;
                    roomsArray[currentX][currentY] = i + 2;
                }
            } else if (pathGen == 1) {
                if (currentY == 4 || roomsArray[currentX][currentY + 1] != 0) {
                    i--;
                } else {
                    currentY++;
                    roomsArray[currentX][currentY] = i + 2;
                }
            } else {
                if (currentY == 0 || roomsArray[currentX][currentY - 1] != 0) {
                    i--;
                } else {
                    currentY--;
                    roomsArray[currentX][currentY] = i + 2;
                }
            }
        }
    }

    public int[][] getRoomsArray() {
        return roomsArray;
    }

    public boolean[] getRoomsAccessed() {
        return roomsAccessed;
    }

    public boolean[] getMonstersDefeated() {
        return monstersDefeated;
    }

    public String getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
    }
}
