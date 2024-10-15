package be.cegeka.battle;

import org.junit.jupiter.api.Test;

import static be.cegeka.battle.TestConstants.NAME_JOHN_DOE;
import static be.cegeka.battle.TestConstants.NAME_RON_DOE;
import static be.cegeka.battle.weapon.Weapon.AXE;
import static be.cegeka.battle.weapon.Weapon.SWORD;
import static org.assertj.core.api.Assertions.assertThat;

class ArmyTest {

    @Test
    void enrollSoldier_expectSoldierToEnrolledAndToBeFrontMan() {
        Army army = new Army();
        Soldier soldier = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier);

        assertThat(army.getFrontMan()).isEqualTo(soldier);
        assertThat(army.getSoldiers()).containsExactly(soldier);
    }

    @Test
    void givenSoldierIsEnrolled_whenEnrollSoldier_expectSoldierOnlyOnceInArmy() {
        Army army = new Army();
        Soldier soldier = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier);
        army.enroll(soldier);

        assertThat(army.getFrontMan()).isEqualTo(soldier);
        assertThat(army.getSoldiers()).containsExactly(soldier);
    }

    @Test
    void givenSoldierIsEnrolled_whenAnotherSoldierEnrolls_expectBothSoldiersInArmyAndFrontManIsFirstSoldier() {
        Army army = new Army();
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier soldier2 = new Soldier(NAME_RON_DOE, AXE);

        army.enroll(soldier1);
        army.enroll(soldier2);

        assertThat(army.getFrontMan()).isEqualTo(soldier1);
        assertThat(army.getSoldiers()).containsExactly(soldier1, soldier2);
    }
}
