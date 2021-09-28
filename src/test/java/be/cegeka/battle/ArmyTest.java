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
}
