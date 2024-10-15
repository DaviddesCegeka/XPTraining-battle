package be.cegeka.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Army {

    public final List<Soldier> soldiers = new ArrayList<>();
    public Soldier frontMan;

    public Army() {
    }

    public void enroll(Soldier soldier) {
        if (soldiers.contains(soldier)) {
            return;
        }

        if (soldiers.isEmpty()) {
            frontMan = soldier;
        }
        soldiers.add(soldier);
    }

    public void removeFrontman() {
        if (frontMan == null) {
            throw new IllegalStateException("The army has no frontman to be removed");
        }
        soldiers.remove(frontMan);
        frontMan = soldiers.stream().findFirst().orElse(null);
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public Optional<Soldier> getFrontMan() {
        return ofNullable(frontMan);
    }
}
