package be.cegeka.battle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Army {

    private Soldier frontMan;
    private Collection<Soldier> soldiers = new ArrayList();

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

    public Collection<Soldier> getEnrolledSoldiers() {
        return soldiers;
    }

    public Optional<Soldier> getFrontMan() {
        return Optional.ofNullable(frontMan);
    }
}
