package be.cegeka.battle;

import java.util.ArrayList;
import java.util.List;

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

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public Soldier getFrontMan() {
        return frontMan;
    }
}
