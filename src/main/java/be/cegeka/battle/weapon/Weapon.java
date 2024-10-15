package be.cegeka.battle.weapon;

public enum Weapon {
    BARE_FIST(1),
    AXE(3),
    SWORD(2),
    SPEAR(2);

    private final int weaponStrength;

    Weapon(int weaponStrength) {
        this.weaponStrength = weaponStrength;
    }

    public int getWeaponStrength() {
        return weaponStrength;
    }
}
