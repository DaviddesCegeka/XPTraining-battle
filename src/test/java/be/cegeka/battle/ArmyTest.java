package be.cegeka.battle;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArmyTest {

    @Test
    public void givenAnArmyWithOneSoldier_thenSoldierIsInArmy() {
        Soldier soldier = new Soldier("soldier");
        Army army = new Army();
        army.enroll(soldier);

        assertThat(army.getEnrolledSoldiers()).containsExactly(soldier);
    }

    @Test
    public void givenAnArmyWithTwoSoldiers_thenSoldiersAreInArmy() {
        Soldier soldier1 = new Soldier("soldier");
        Soldier soldier2 = new Soldier("soldier");
        Army army = new Army();
        army.enroll(soldier1);
        army.enroll(soldier2);

        assertThat(army.getEnrolledSoldiers()).containsExactly(soldier1, soldier2);
    }

    @Test
    public void givenAnArmyWithoutSoldiers_thenNoSoldiersAreInArmy() {
        Army army = new Army();

        assertThat(army.getEnrolledSoldiers()).isEmpty();
    }

    @Test
    public void givenAnArmy_whenAddingNull_thenThrowsException() {
        Army army = new Army();

        assertThatThrownBy(() -> army.enroll(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Soldier cannot be null");
    }

    @Test
    public void givenAnArmyWithTwoSoldiers_thenFrontManIsFirstEnrolledSoldier() {
        Soldier soldier1 = new Soldier("soldier");
        Soldier soldier2 = new Soldier("soldier");
        Army army = new Army();
        army.enroll(soldier1);
        army.enroll(soldier2);

        assertThat(army.getFrontMan()).isEqualTo(Optional.of(soldier1));
    }

    @Test
    public void givenAnArmyWithoutSoldiers_thenNoFrontManExists() {
        Army army = new Army();
        assertThat(army.getFrontMan()).isEmpty();
    }

    @Test
    public void givenAnArmyWithOneSoldiers_thenFrontManIsFirstEnrolledSoldier() {
        Soldier soldier1 = new Soldier("soldier");
        Army army = new Army();
        army.enroll(soldier1);

        assertThat(army.getFrontMan()).isEqualTo(Optional.of(soldier1));
    }

    @Test
    public void givenTwoArmiesWithOneSoldierWithOtherWeapons_whenEngagedInWar_armyWithFrontmanWithStrongestWeaponWins() {
        Soldier soldier1 = new Soldier("soldier");
        Army attackingArmy = new Army();
        attackingArmy.enroll(soldier1);

        Soldier soldier2 = new Soldier("soldier");
        soldier2.equipWeapon(new Sword());
        Army defendingArmy = new Army();
        defendingArmy.enroll(soldier2);

        Army winningArmy = attackingArmy.engageInWar(defendingArmy);

        assertThat(winningArmy).isEqualTo(defendingArmy);
        assertThat(defendingArmy.getEnrolledSoldiers()).containsExactly(soldier2);
        assertThat(defendingArmy.getFrontMan()).isPresent();
        assertThat(attackingArmy.getEnrolledSoldiers()).isEmpty();
        assertThat(attackingArmy.getFrontMan()).isEmpty();
    }

    @Test
    public void givenTwoArmiesWithOneSoldierWithSameWeapon_whenEngagedInWar_attackingArmyWins() {
        Soldier soldier1 = new Soldier("soldier");
        Army attackingArmy = new Army();
        attackingArmy.enroll(soldier1);

        Soldier soldier2 = new Soldier("soldier");
        Army defendingArmy = new Army();
        defendingArmy.enroll(soldier2);

        Army winningArmy = attackingArmy.engageInWar(defendingArmy);

        assertThat(winningArmy).isEqualTo(attackingArmy);
        assertThat(winningArmy.getEnrolledSoldiers()).containsExactly(soldier1);
        assertThat(winningArmy.getFrontMan()).isPresent();
        assertThat(defendingArmy.getEnrolledSoldiers()).isEmpty();
        assertThat(defendingArmy.getFrontMan()).isEmpty();
    }

    @Test
    public void givenTwoArmiesAndAttackingArmyHasNoSoldiers_whenEngagedInWar_thenThrowsException() {
        Army attackingArmy = new Army();

        Soldier soldier = new Soldier("soldier");
        Army defendingArmy = new Army();
        defendingArmy.enroll(soldier);

        assertThatThrownBy(() -> attackingArmy.engageInWar(defendingArmy))
            .isInstanceOf(IllegalStateException.class)
            .withFailMessage("Cannot engage in war if the army has no frontman");
    }

    @Test
    public void givenTwoArmiesAndDefendingArmyHasNoSoldiers_whenEngagedInWar_thenAttackingArmyWins() {
        Soldier soldier = new Soldier("soldier");
        Army attackingArmy = new Army();
        attackingArmy.enroll(soldier);

        Army defendingArmy = new Army();

        Army winningArmy = attackingArmy.engageInWar(defendingArmy);

        assertThat(winningArmy).isEqualTo(attackingArmy);
    }

    @Test
    public void givenTwoArmiesWithMultipleSoldiers_whenEngagedInWar_thenAttackingArmyWinsAndDefendingArmyHasNoSoldiers() {
        Army attackingArmy = new Army();
        attackingArmy.enroll(createSoldier(new Sword()));

        Army defendingArmy = new Army();

        Army winningArmy = attackingArmy.engageInWar(defendingArmy);

        assertThat(winningArmy).isEqualTo(attackingArmy);
    }

    private Soldier createSoldier(Weapon weapon) {
        Soldier soldier = new Soldier("soldier");
        soldier.equipWeapon(weapon);
        return soldier;
    }
}
