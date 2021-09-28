package be.cegeka.battle;

import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Soldier {

    private final String name;
    private Weapon weapon = new BareFist();

    public Soldier(String name) {
        Validate.isTrue(isNotBlank(name));

        this.name = name;
    }

    String getName() {
        return this.name;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
