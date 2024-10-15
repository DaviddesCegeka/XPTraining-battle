package be.cegeka.battle;


import org.junit.jupiter.api.Test;

import static be.cegeka.battle.weapon.Weapon.BARE_FIST;
import static be.cegeka.battle.weapon.Weapon.SWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SoldierTest {

    public static final String NAME_JOHN_DOE = "John Doe";
    public static final String INVALID_NAME_EXCEPTION_MESSAGE = "A soldier must have a name";

    @Test
    void construction_aSoldierMustHaveAName() {
        Soldier soldier = new Soldier(NAME_JOHN_DOE);

        assertThat(soldier.getName()).isEqualTo(NAME_JOHN_DOE);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeNull() {
        assertThatThrownBy(() -> new Soldier(null))
                .hasMessage(INVALID_NAME_EXCEPTION_MESSAGE)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeEmpty() {
        assertThatThrownBy(() -> new Soldier(""))
                .hasMessage(INVALID_NAME_EXCEPTION_MESSAGE)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeBlank() {
        assertThatThrownBy(() -> new Soldier("     "))
                .hasMessage(INVALID_NAME_EXCEPTION_MESSAGE)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_defaultWeaponIsBareFist() {
        Soldier soldier = new Soldier(NAME_JOHN_DOE);

        assertThat(soldier.getWeapon()).isEqualTo(BARE_FIST);
    }

    @Test
    void construction_canEquipDifferentWeapons() {
        Soldier soldier = new Soldier(NAME_JOHN_DOE, SWORD);

        assertThat(soldier.getWeapon()).isEqualTo(SWORD);
    }

}
