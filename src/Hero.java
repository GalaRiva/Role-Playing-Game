public class Hero extends Character {

    public Hero(String name, int healthPoints, int strength, int dexterity, int xp, int gold) {
        super(name, healthPoints, strength, dexterity, xp, gold);
    }

    @Override
    public void decreaseGold(int goldAmount) {

    }
}
