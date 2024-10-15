package be.cegeka.battle;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static be.cegeka.battle.TestConstants.NAME_JOHN_DOE;
import static be.cegeka.battle.TestConstants.NAME_RON_DOE;
import static be.cegeka.battle.Weapon.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WarTest {

    IHeadquarters attackingHQ = Mockito.mock(IHeadquarters.class);
    IHeadquarters defendingHQ = Mockito.mock(IHeadquarters.class);

    @Test
    void givenAtackerIsStronger_getWinningArmy_expectAttackerWins() {
        when(attackingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        when(attackingHQ.reportEnlistment(NAME_RON_DOE)).thenReturn(2);
        Army attackingArmy = new Army(attackingHQ);
        Soldier attackingSoldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier attackingSoldier2 = new Soldier(NAME_RON_DOE, AXE);
        attackingArmy.enroll(attackingSoldier1);
        attackingArmy.enroll(attackingSoldier2);

        when(defendingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        when(defendingHQ.reportEnlistment(NAME_RON_DOE)).thenReturn(2);
        Army defendingArmy = new Army(defendingHQ);
        Soldier defendingSoldier1 = new Soldier(NAME_JOHN_DOE, AXE);
        Soldier defendingSoldier2 = new Soldier(NAME_RON_DOE, BARE_FIST);
        defendingArmy.enroll(defendingSoldier1);
        defendingArmy.enroll(defendingSoldier2);

        War war = new War(attackingArmy, defendingArmy);

        Army winningArmy = war.getWinningArmy();

        assertThat(winningArmy).isEqualTo(attackingArmy);
        assertThat(attackingArmy.getFrontMan()).hasValue(attackingSoldier2);
        assertThat(attackingArmy.getSoldiers()).containsExactly(attackingSoldier2);
        assertThat(defendingArmy.getFrontMan()).isEmpty();
        assertThat(defendingArmy.getSoldiers()).isEmpty();
        verify(attackingHQ).reportCasualty(1);
        verify(defendingHQ).reportCasualty(1);
        verify(defendingHQ).reportCasualty(2);
        verify(attackingHQ).reportVictory(1);
    }

    @Test
    void givenDefenderIsStronger_getWinningArmy_expectDefenderWins() {
        when(attackingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        when(attackingHQ.reportEnlistment(NAME_RON_DOE)).thenReturn(2);
        Army attackingArmy = new Army(attackingHQ);
        Soldier attackingSoldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier attackingSoldier2 = new Soldier(NAME_RON_DOE, BARE_FIST);
        attackingArmy.enroll(attackingSoldier1);
        attackingArmy.enroll(attackingSoldier2);

        when(defendingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        when(defendingHQ.reportEnlistment(NAME_RON_DOE)).thenReturn(2);
        Army defendingArmy = new Army(defendingHQ);
        Soldier defendingSoldier1 = new Soldier(NAME_JOHN_DOE, AXE);
        Soldier defendingSoldier2 = new Soldier(NAME_RON_DOE, BARE_FIST);
        defendingArmy.enroll(defendingSoldier1);
        defendingArmy.enroll(defendingSoldier2);

        War war = new War(attackingArmy, defendingArmy);

        Army winningArmy = war.getWinningArmy();

        assertThat(winningArmy).isEqualTo(defendingArmy);
        assertThat(attackingArmy.getFrontMan()).isEmpty();
        assertThat(attackingArmy.getSoldiers()).isEmpty();
        assertThat(defendingArmy.getFrontMan()).hasValue(defendingSoldier1);
        assertThat(defendingArmy.getSoldiers()).containsExactly(defendingSoldier1, defendingSoldier2);
        verify(attackingHQ).reportCasualty(1);
        verify(attackingHQ).reportCasualty(2);
        verify(defendingHQ).reportVictory(2);
    }

    @Test
    void givenArmyWithEqualStrenght_getWinningArmy_expectAttacker() {
        when(attackingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        Army attackingArmy = new Army(attackingHQ);
        Soldier attackingSoldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        attackingArmy.enroll(attackingSoldier1);

        when(defendingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        Army defendingArmy = new Army(defendingHQ);
        Soldier defendingSoldier1 = new Soldier(NAME_JOHN_DOE, SWORD);
        defendingArmy.enroll(defendingSoldier1);

        War war = new War(attackingArmy, defendingArmy);

        Army winningArmy = war.getWinningArmy();

        assertThat(winningArmy).isEqualTo(attackingArmy);
        verify(defendingHQ).reportCasualty(1);
        verify(attackingHQ).reportVictory(1);
    }

    @Test
    void construction_whenAttackingArmyIsEmpty_expectException() {
        Army attackingArmy = new Army(attackingHQ);

        when(defendingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        Army defendingArmy = new Army(defendingHQ);
        Soldier defendingSoldier = new Soldier(NAME_JOHN_DOE, AXE);
        defendingArmy.enroll(defendingSoldier);

        assertThatThrownBy(() -> new War(attackingArmy, defendingArmy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("An army without soldiers cannot fight");
    }

    @Test
    void construction_whenDefendingArmyIsEmpty_expectException() {
        when(attackingHQ.reportEnlistment(NAME_JOHN_DOE)).thenReturn(1);
        Army attackingArmy = new Army(attackingHQ);
        Soldier attackingSoldier = new Soldier(NAME_JOHN_DOE, SWORD);
        attackingArmy.enroll(attackingSoldier);

        Army defendingArmy = new Army(defendingHQ);

        assertThatThrownBy(() -> new War(attackingArmy, defendingArmy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("An army without soldiers cannot fight");
    }

    @Test
    void construction_whenAttackingAndDefendingArmyAreEmpty_expectException() {
        Army attackingArmy = new Army(attackingHQ);
        Army defendingArmy = new Army(defendingHQ);

        assertThatThrownBy(() -> new War(attackingArmy, defendingArmy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("An army without soldiers cannot fight");
    }
}
