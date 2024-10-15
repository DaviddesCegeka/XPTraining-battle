package be.cegeka.battle;

import java.util.*;

import static java.util.Optional.ofNullable;

public class Army {

    private final IHeadquarters headquarters;
    private final Map<Integer, Soldier> soldiers = new HashMap<>();
    private Soldier frontMan;

    public Army(IHeadquarters headquarters) {
        this.headquarters = headquarters;
    }

    public void enroll(Soldier soldier) {
        if (soldiers.containsValue(soldier)) {
            return;
        }

        if (soldiers.isEmpty()) {
            frontMan = soldier;
        }
        soldiers.put(headquarters.reportEnlistment(soldier.getName()), soldier);
    }

    public void removeFrontman() {
        if (frontMan == null) {
            throw new IllegalStateException("The army has no frontman to be removed");
        }
        Integer frontmanId = getSoldierId(frontMan);
        soldiers.remove(frontmanId);
        headquarters.reportCasualty(frontmanId);
        frontMan = soldiers.values().stream().findFirst().orElse(null);
    }

    public void reportWonWar() {
        headquarters.reportVictory(soldiers.size());
    }

    public List<Soldier> getSoldiers() {
        return soldiers.values().stream().toList();
    }

    public Soldier getSoldier(Integer soldierId) {
        return soldiers.get(soldierId);
    }

    public boolean isEmpty() {
        return soldiers.isEmpty();
    }

    public Optional<Soldier> getFrontMan() {
        return ofNullable(frontMan);
    }

    private Integer getSoldierId(Soldier soldier) {
        return soldiers.entrySet().stream()
                .filter(entry -> entry.getValue().equals(soldier))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();
    }
}
