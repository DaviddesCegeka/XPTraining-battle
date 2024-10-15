package be.cegeka.battle;


import org.junit.jupiter.api.Test;

import static be.cegeka.battle.TestConstants.NAME_JOHN_DOE;
import static be.cegeka.battle.TestConstants.NAME_RON_DOE;
import static be.cegeka.battle.weapon.Weapon.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SoldierTest {

    @Test
    void construction_aSoldierMustHaveAName() {
        Soldier soldier = new Soldier(NAME_JOHN_DOE);

        assertThat(soldier.getName()).isEqualTo(NAME_JOHN_DOE);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeNull() {
        assertThatThrownBy(() -> new Soldier(null))
                .hasMessage(TestConstants.INVALID_NAME_EXCEPTION_MESSAGE)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeEmpty() {
        assertThatThrownBy(() -> new Soldier(""))
                .hasMessage(TestConstants.INVALID_NAME_EXCEPTION_MESSAGE)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeBlank() {
        assertThatThrownBy(() -> new Soldier("     "))
                .hasMessage(TestConstants.INVALID_NAME_EXCEPTION_MESSAGE)
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

    @Test
    void attack_whenAttackerHasStrongerWeapon_expectAttackerWins() {
        Soldier attacker = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier defender = new Soldier(NAME_RON_DOE, BARE_FIST);

        Soldier winner = attacker.attack(defender);

        assertThat(winner).isEqualTo(attacker);
    }
}
