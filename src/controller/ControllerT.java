package controller;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ConfigurationScreen;
import view.GameScreen;
import view.WelcomeScreen;
import view.WinScreen;

import java.util.Arrays;



public class ControllerT extends Application {
    private Stage mainWindow;
    private final int width = 1280;
    private final int height = 720;
    private int[][] roomsArray = new int[5][5];
    private int currentRoomX = 0;
    private int currentRoomY = 2;
    private String difficulty;
    private String weapon;
    private boolean[] roomsAccessed = new boolean[9];


    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Welcome to the Sanctum!");
        randomizeRooms();
        clearRooms();
        setRooms(1, 0, 2);
        setRooms(2, 1, 2);
        setRooms(3, 1, 3);
        setRooms(4, 2, 3);
        setRooms(5, 2, 4);
        setRooms(6, 3, 4);
        setRooms(7, 3, 3);
        setRooms(8, 4, 3);
        setRooms(9, 4, 2);
        System.out.println(Arrays.deepToString(roomsArray));
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(width, height);
        Button startButton = welcomeScreen.getStartButton();
        startButton.setOnAction(e -> goToConfigScreen());
        Button quitButton = welcomeScreen.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());
        Scene scene = welcomeScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToConfigScreen() {
        ConfigurationScreen configScreen = new ConfigurationScreen();
        Button nextScreenButton = configScreen.getNextScreenButton();
        nextScreenButton.setOnAction(e -> goToGameScreen(configScreen.getDifficulty(),
                configScreen.getWeapon()));
        Scene scene = configScreen.showConfigScreen();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToGameScreen(String selectedDifficulty, String selectedWeapons) {
        difficulty = selectedDifficulty;
        weapon = selectedWeapons;
        roomsAccessed[0] = true;
        GameScreen game = new GameScreen(selectedDifficulty, selectedWeapons, 1);
        Button buttonUp = game.getButtonUp();
        Button buttonDown = game.getButtonDown();
        Button buttonLeft = game.getButtonLeft();
        Button buttonRight = game.getButtonRight();
        buttonUp.setOnAction(e -> roomManager("Up"));
        buttonDown.setOnAction(e -> roomManager("Down"));
        buttonLeft.setOnAction(e -> roomManager("Left"));
        buttonRight.setOnAction(e -> roomManager("Right"));
        Scene scene = game.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void switchRoom() {
        GameScreen room = new GameScreen(difficulty,
                weapon, roomsArray[currentRoomX][currentRoomY]);
        Button buttonUp = room.getButtonUp();
        Button buttonDown = room.getButtonDown();
        Button buttonLeft = room.getButtonLeft();
        Button buttonRight = room.getButtonRight();
        buttonUp.setOnAction(e -> roomManager("Up"));
        buttonDown.setOnAction(e -> roomManager("Down"));
        buttonLeft.setOnAction(e -> roomManager("Left"));
        buttonRight.setOnAction(e -> roomManager("Right"));
        Scene scene = room.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToWinScreen() {
        WinScreen winner = new WinScreen();
        Scene scene = winner.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
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

    public void roomManager(String direction) {
        switch (direction) {
        case "Up":
            if (currentRoomY == 0) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of the dungeon "
                        + "and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
            } else {
                currentRoomY--;
                if (roomsArray[currentRoomX][currentRoomY] != 0
                        && roomsArray[currentRoomX][currentRoomY] != 1) {
                    roomValidity(direction);
                }
                switchRoom();
            }
            break;
        case "Down":
            if (currentRoomY == 4) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of the dungeon "
                        + "and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
            } else {
                currentRoomY++;
                if (roomsArray[currentRoomX][currentRoomY] != 0
                        && roomsArray[currentRoomX][currentRoomY] != 1) {
                    roomValidity(direction);
                }
                switchRoom();
            }
            break;
        case "Right":
            if (roomsArray[currentRoomX][currentRoomY] == 9) {
                goToWinScreen();
                return;
            }
            if (currentRoomX == 4) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of the dungeon "
                        + "and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
            } else {
                currentRoomX++;
                if (roomsArray[currentRoomX][currentRoomY] != 0
                        && roomsArray[currentRoomX][currentRoomY] != 1) {
                    roomValidity(direction);
                }
                switchRoom();
            }
            break;
        case "Left":
            if (currentRoomX == 0) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of the dungeon "
                        + "and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
            } else {
                currentRoomX--;
                if (roomsArray[currentRoomX][currentRoomY] != 0
                        && roomsArray[currentRoomX][currentRoomY] != 1) {
                    roomValidity(direction);
                }
                switchRoom();
            }
            break;
        default:
            break;
        }
    }

    public void roomValidity(String direction) {
        if (roomsAccessed[roomsArray[currentRoomX][currentRoomY] - 2]) {
            roomsAccessed[roomsArray[currentRoomX][currentRoomY] - 1] = true;
        } else {
            Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
            invalidRoom.setContentText("You are attempting to access room "
                    + roomsArray[currentRoomX][currentRoomY]
                    + " and you must access room "
                    + (roomsArray[currentRoomX][currentRoomY] - 1) + " first!");
            invalidRoom.setHeaderText("Choose a different exit");
            invalidRoom.setTitle("Invalid Exit");
            invalidRoom.show();
            switch (direction) {
            case "Up":
                currentRoomY++;
                break;
            case "Down":
                currentRoomY--;
                break;
            case "Right":
                currentRoomX--;
                break;
            case "Left":
                currentRoomX++;
                break;
            default:
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public int getCurrentRoomX() {
        return currentRoomX;
    }

    public int getCurrentRoomY() {
        return currentRoomY;
    }

    public void setRooms(int roomsNum, int x, int y) {
        roomsArray[x][y] = roomsNum;
    }

    public void clearRooms() {
        int[][] tempArray = new int[5][5];
        roomsArray = tempArray;
    }


}
