package components;

public class Monster {
    private int monsterIndex;
    private String monsterName;
    private int monsterHP;

    public Monster(int roomNo) {
        monsterIndex = roomNo;
        monsterName = returnName(monsterIndex);
        monsterHP = returnHP(monsterIndex);
    }

    private int returnHP(int monsterIndex) {
        if (monsterIndex <= 1) {
            return 0;
        }
        if (monsterIndex % 2 == 0) {
            return 10;
        } else if (monsterIndex != 9) {
            return 5;
        } else {
            return 20;
        }
    }

    private String returnName(int monsterIndex) {
        if (monsterIndex <= 1) {
            return "";
        }
        if (monsterIndex % 2 == 0) {
            return "Goblin";
        } else if (monsterIndex != 9) {
            return "Goblin Commander";
        } else {
            return "Viper";
        }
    }

    public int getMonsterIndex() {
        return monsterIndex;
    }

    public void setMonsterIndex(int monsterIndex) {
        this.monsterIndex = monsterIndex;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public int getMonsterHP() {
        return monsterHP;
    }

    public void setMonsterHP(int monsterHP) {
        this.monsterHP = monsterHP;
    }
}
