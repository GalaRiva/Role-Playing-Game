import java.util.Random;

public class Character {
    private String name;
    private int agility;
    private int health;
    private int experience;
    private int gold;
    private int power;

    public Character(String name, int agility, int health, int experience, int gold, int power) {
        this.name = name;
        this.agility = agility;
        this.health = health;
        this.experience = experience;
        this.gold = gold;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getAgility() {
        return agility;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public int getPower() {
        return power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int attack() {
        Random random = new Random();
        int minDamage = 5;
        int maxDamage = 15;
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void decreaseGold(int goldAmount) {
        if (this.gold >= goldAmount) {
            this.gold -= goldAmount;
            System.out.println("У героя " + this.name + " было списано " + goldAmount + " золотых. Теперь у него " + this.gold + " золота.");
        } else {
            System.out.println("У героя " + this.name + " недостаточно золота для сделки " + goldAmount + " золотых.");
        }
    }
}