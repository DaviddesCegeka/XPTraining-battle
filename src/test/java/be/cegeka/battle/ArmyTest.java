package be.cegeka.battle;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static be.cegeka.battle.TestConstants.NAME_JOHN_DOE;
import static be.cegeka.battle.TestConstants.NAME_RON_DOE;
import static be.cegeka.battle.Weapon.AXE;
import static be.cegeka.battle.Weapon.SWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArmyTest {

    IHeadquarters headquarters = Mockito.mock(IHeadquarters.class);

    @Test
    void enrollSoldier_expectSoldierToEnrolledAndToBeFrontManAndSoldierHasID() {
        when(headquarters.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);

        Army army = new Army(headquarters);
        Soldier soldier = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier);

        assertThat(army.getFrontMan()).hasValue(soldier);
        assertThat(army.getSoldiers()).containsExactly(soldier);
        assertThat(army.getSoldier(1)).isEqualTo(soldier);
    }

    @Test
    void givenSoldierIsEnrolled_whenEnrollSoldier_expectSoldierOnlyOnceInArmy() {
        Army army = new Army(headquarters);
        Soldier soldier = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier);
        army.enroll(soldier);

        assertThat(army.getFrontMan()).hasValue(soldier);
        assertThat(army.getSoldiers()).containsExactly(soldier);
    }

    @Test
    void givenSoldierIsEnrolled_whenAnotherSoldierEnrolls_expectBothSoldiersInArmyAndFrontManIsFirstSoldierAndSoldiersHaveId() {
        when(headquarters.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        when(headquarters.reportEnlistment(NAME_RON_DOE)).thenReturn(2);

        Army army = new Army(headquarters);
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier soldier2 = new Soldier(NAME_RON_DOE, AXE);

        army.enroll(soldier1);
        army.enroll(soldier2);

        assertThat(army.getFrontMan()).hasValue(soldier1);
        assertThat(army.getSoldiers()).containsExactly(soldier1, soldier2);
        assertThat(army.getSoldier(1)).isEqualTo(soldier1);
        assertThat(army.getSoldier(2)).isEqualTo(soldier2);
    }

    @Test
    void givenArmyWithTwoSoldiers_whenFrontmanIsRemoved_expectOneSoldierAndNewFrontman() {
        when(headquarters.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        when(headquarters.reportEnlistment(NAME_RON_DOE)).thenReturn(2);

        Army army = new Army(headquarters);
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier soldier2 = new Soldier(NAME_RON_DOE, AXE);

        army.enroll(soldier1);
        army.enroll(soldier2);

        army.removeFrontman();

        assertThat(army.getFrontMan()).hasValue(soldier2);
        assertThat(army.getSoldiers()).containsExactly(soldier2);
        verify(headquarters).reportCasualty(1);
    }

    @Test
    void givenArmyWithOneSoldiers_whenFrontmanIsRemoved_expectNoMoreSoldiersAndNoFrontman() {
        when(headquarters.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        Army army = new Army(headquarters);
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier1);

        army.removeFrontman();

        assertThat(army.getFrontMan()).isEmpty();
        assertThat(army.getSoldiers()).isEmpty();
        verify(headquarters).reportCasualty(1);
    }

    @Test
    void givenArmyWithoutSoldiers_whenFrontmanIsRemoved_expectException() {
        Army army = new Army(headquarters);

        assertThatThrownBy(army::removeFrontman)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("The army has no frontman to be removed");
    }

    @Test
    void givenArmyWithOneSoldiers_whenIsEmptyIsCalled_expectFalse() {
        when(headquarters.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        Army army = new Army(headquarters);
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);

        army.enroll(soldier1);

        assertThat(army.isEmpty()).isFalse();
    }

    @Test
    void givenArmyWithoutSoldiers_whenIsEmptyIsCalled_expectTrue() {
        Army army = new Army(headquarters);

        assertThat(army.isEmpty()).isTrue();
    }

    @Test
    void givenArmyWithTwoSoldiers_whenReportWonWar_expectReportGoesToHeadquarter() {
        when(headquarters.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        when(headquarters.reportEnlistment(NAME_RON_DOE)).thenReturn(2);
        Army army = new Army(headquarters);
        Soldier soldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier soldier2 = new Soldier(NAME_RON_DOE, SWORD);
        army.enroll(soldier1);
        army.enroll(soldier2);

        army.reportWonWar();

        verify(headquarters).reportVictory(2);
    }
}
