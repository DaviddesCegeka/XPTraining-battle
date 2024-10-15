package be.cegeka.battle;

import org.junit.jupiter.api.Test;

import static be.cegeka.battle.TestConstants.NAME_JOHN_DOE;
import static be.cegeka.battle.TestConstants.NAME_RON_DOE;
import static be.cegeka.battle.Weapon.AXE;
import static be.cegeka.battle.Weapon.SWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArmyTest {

    @Test
    void enrollSoldier_expectSoldierToEnrolledAndToBeFrontMan() {
        Army army = new Army();
        Soldier soldier = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier);

        assertThat(army.getFrontMan()).hasValue(soldier);
        assertThat(army.getSoldiers()).containsExactly(soldier);
    }

    @Test
    void givenSoldierIsEnrolled_whenEnrollSoldier_expectSoldierOnlyOnceInArmy() {
        Army army = new Army();
        Soldier soldier = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier);
        army.enroll(soldier);

        assertThat(army.getFrontMan()).hasValue(soldier);
        assertThat(army.getSoldiers()).containsExactly(soldier);
    }

    @Test
    void givenSoldierIsEnrolled_whenAnotherSoldierEnrolls_expectBothSoldiersInArmyAndFrontManIsFirstSoldier() {
        Army army = new Army();
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier soldier2 = new Soldier(NAME_RON_DOE, AXE);

        army.enroll(soldier1);
        army.enroll(soldier2);

        assertThat(army.getFrontMan()).hasValue(soldier1);
        assertThat(army.getSoldiers()).containsExactly(soldier1, soldier2);
    }

    @Test
    void givenArmyWithTwoSoldiers_whenFrontmanIsRemoved_expectOneSoldierAndNewFrontman() {
        Army army = new Army();
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier soldier2 = new Soldier(NAME_RON_DOE, AXE);

        army.enroll(soldier1);
        army.enroll(soldier2);

        army.removeFrontman();

        assertThat(army.getFrontMan()).hasValue(soldier2);
        assertThat(army.getSoldiers()).containsExactly(soldier2);
    }

    @Test
    void givenArmyWithOneSoldiers_whenFrontmanIsRemoved_expectNoMoreSoldiersAndNoFrontman() {
        Army army = new Army();
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier1);

        army.removeFrontman();

        assertThat(army.getFrontMan()).isEmpty();
        assertThat(army.getSoldiers()).isEmpty();
    }

    @Test
    void givenArmyWithoutSoldiers_whenFrontmanIsRemoved_expectException() {
        Army army = new Army();

        assertThatThrownBy(army::removeFrontman)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("The army has no frontman to be removed");
    }

    @Test
    void givenArmyWithOneSoldiers_whenIsEmptyIsCalled_expectFalse() {
        Army army = new Army();
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier1);

        assertThat(army.isEmpty()).isFalse();
    }

    @Test
    void givenArmyWithoutSoldiers_whenIsEmptyIsCalled_expectTrue() {
        Army army = new Army();

        assertThat(army.isEmpty()).isTrue();
    }
}
