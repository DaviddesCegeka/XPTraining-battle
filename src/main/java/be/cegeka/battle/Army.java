package be.cegeka.battle;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Army {

    private Soldier frontMan;
    private List<Soldier> soldiers = new LinkedList<>();

    public void enroll(Soldier soldier) {
        validateSoldier(soldier);
        addSoldier(soldier);
    }

    private void addSoldier(Soldier soldier) {
        if (soldiers.isEmpty()) {
            frontMan = soldier;
        }
        soldiers.add(soldier);
    }

    private void validateSoldier(Soldier soldier) {
        if (soldier == null) {
            throw new IllegalArgumentException("Soldier cannot be null");
        }
    }

    public List<Soldier> getEnrolledSoldiers() {
        return soldiers;
    }

    public Optional<Soldier> getFrontMan() {
        return Optional.ofNullable(frontMan);
    }

    public Army engageInWar(Army defendingArmy) {
        validateFrontmanPresent();

        if (!defendingArmy.getFrontMan().isPresent()) {
            return this;
        }

        attackFrontman(defendingArmy);

        return this.getEnrolledSoldiers().isEmpty() ? defendingArmy : this;
    }

    private void attackFrontman(Army otherArmy) {
        Soldier winningFrontMan = this.frontMan.attack(otherArmy.getFrontMan().get());
        if (winningFrontMan == this.frontMan) {
            otherArmy.removeFrontman();
        } else {
            this.removeFrontman();
        }
    }

    private void validateFrontmanPresent() {
        if (!getFrontMan().isPresent()) {
            throw new IllegalStateException("Cannot engage in war if the army has no frontman");
        }
    }

    private void removeFrontman() {
        this.getEnrolledSoldiers().remove(this.frontMan);
        this.frontMan = this.getEnrolledSoldiers().isEmpty() ? null : this.getEnrolledSoldiers().get(0);
    }
}
