package be.cegeka.battle;


public class Soldier {

    private final String name;

    public Soldier(String name) {
        checkIfNameIsValid(name);

        this.name = name;
    }

    String getName() {
        return this.name;
    }

    private static void checkIfNameIsValid(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A soldier must have a name");
        }
    }
}
