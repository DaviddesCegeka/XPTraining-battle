package be.cegeka.battle;

public class War {
    private final Army attackingArmy;
    private final Army defendingArmy;

    public War(Army attackingArmy, Army defendingArmy) {
        checkValidArmy(attackingArmy);
        checkValidArmy(defendingArmy);
        this.attackingArmy = attackingArmy;
        this.defendingArmy = defendingArmy;
    }

    public Army getWinningArmy() {
        if (attackingArmy.isEmpty()) {
            defendingArmy.reportWonWar();
            return defendingArmy;
        }
        if (defendingArmy.isEmpty()) {
            attackingArmy.reportWonWar();
            return attackingArmy;
        }
        letFrontmenFight(attackingArmy.getFrontMan().orElseThrow(), defendingArmy.getFrontMan().orElseThrow());
        return getWinningArmy();
    }

    private void letFrontmenFight(Soldier attackingFrontMan, Soldier defendingFrontMan) {
        Fight fight = new Fight(attackingFrontMan, defendingFrontMan);
        Soldier winner = fight.getWinner();

        if (winner.equals(attackingFrontMan)) {
            defendingArmy.removeFrontman();
        } else {
            attackingArmy.removeFrontman();
        }
    }

    private static void checkValidArmy(Army army) {
        if (army == null || army.isEmpty()) {
            throw new IllegalArgumentException("An army without soldiers cannot fight");
        }
    }
}
