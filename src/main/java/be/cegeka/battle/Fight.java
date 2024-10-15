package be.cegeka.battle;

public class Fight {
    private final Soldier attacker;
    private final Soldier defender;

    public Fight(Soldier attacker, Soldier defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public Soldier getWinner() {
        return getAttackingWeaponStrength() >= getDefendingWeaponStrength() ? attacker : defender;
    }

    private int getAttackingWeaponStrength() {
        return attacker.getWeapon().getWeaponStrength();
    }

    private int getDefendingWeaponStrength() {
        return defender.getWeapon().getWeaponStrength();
    }
}
