package be.cegeka.battle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SoldierTest {
    final String SOLDIER_NAME = "name";
    final String ANOTHER_SOLDIER_NAME = "anotherName";

    @Test
    public void construction_ASoldierMustHaveAName() {
        Soldier soldier = new Soldier(SOLDIER_NAME);

        assertThat(soldier.getName()).isEqualTo(SOLDIER_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeNull() {
        new Soldier(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeEmpty() {
        new Soldier("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeBlank() {
        new Soldier("   ");
    }

    @Test
    public void construction_ASoldierHasADefaultWeaponBareFist() {
        Soldier soldier = new Soldier(SOLDIER_NAME);

        assertThat(soldier.getWeapon()).isInstanceOf(BareFist.class);
    }

    @Test
    public void givenSoldier_whenSetSwordAsWeapon_thenSoldierHasSword() {
        Soldier soldier = new Soldier(SOLDIER_NAME);
        soldier.equipWeapon(new Sword());

        assertThat(soldier.getWeapon()).isInstanceOf(Sword.class);
    }

    @Test
    public void givenSoldier_whenSetAxeAsWeapon_thenSoldierHasAxe() {
        Soldier soldier = new Soldier(SOLDIER_NAME);
        soldier.equipWeapon(new Axe());

        assertThat(soldier.getWeapon()).isInstanceOf(Axe.class);
    }

    @Test
    public void givenSoldier_whenSetSpearAsWeapon_thenSoldierHasSpear() {
        Soldier soldier = new Soldier(SOLDIER_NAME);
        soldier.equipWeapon(new Spear());

        assertThat(soldier.getWeapon()).isInstanceOf(Spear.class);
    }

    @Test
    public void givenTwoSoldiersWithSameWeapons_whenFighting_thenAttackerWins() {

        Soldier attacker = new Soldier(SOLDIER_NAME);
        Soldier defender = new Soldier(ANOTHER_SOLDIER_NAME);

        Soldier winner = attacker.attack(defender);

        assertThat(winner).isEqualTo(attacker);
    }

    @Test
    public void givenTwoSoldiersWithDifferentWeapons_whenAttackerHasWeakestWeapon_thenDefenderWins() {

        Soldier attacker = new Soldier(SOLDIER_NAME);
        Soldier defender = new Soldier(ANOTHER_SOLDIER_NAME);

        defender.equipWeapon(new Sword());

        Soldier winner = attacker.attack(defender);

        assertThat(winner).isEqualTo(defender);
    }

    @Test
    public void givenTwoSoldiersWithDifferentWeapons_whenAttackerHasStrongestWeapon_thenAttackerWins() {

        Soldier attacker = new Soldier(SOLDIER_NAME);
        Soldier defender = new Soldier(ANOTHER_SOLDIER_NAME);

        attacker.equipWeapon(new Sword());

        Soldier winner = attacker.attack(defender);

        assertThat(winner).isEqualTo(attacker);
    }

    @Test
    public void givenTwoSoldiersWithSpearAndSword_whenSoldiersFight_thenAttackerWins() {

        Soldier attacker = new Soldier(SOLDIER_NAME);
        Soldier defender = new Soldier(ANOTHER_SOLDIER_NAME);

        attacker.equipWeapon(new Sword());
        defender.equipWeapon(new Spear());

        Soldier winner = attacker.attack(defender);

        assertThat(winner).isEqualTo(attacker);
    }

    @Test
    public void givenSoldier_whenEquipNull_thenThrowException() {

        Soldier attacker = new Soldier(SOLDIER_NAME);

        assertThatThrownBy(() -> attacker.equipWeapon(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Weapon cannot be null");
    }
}
