package be.cegeka.battle;


import static be.cegeka.battle.Weapon.BARE_FIST;

public class Soldier {

    private final String name;
    private final Weapon weapon;

    public Soldier(String name, Weapon weapon) {
        checkIfNameIsValid(name);

        this.name = name;
        this.weapon = weapon;
    }

    public Soldier(String name) {
        this(name, BARE_FIST);
    }

    String getName() {
        return this.name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Soldier attack(Soldier defender) {
        return new Fight(this, defender).getWinner();
    }

    private static void checkIfNameIsValid(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A soldier must have a name");
        }
    }
}
