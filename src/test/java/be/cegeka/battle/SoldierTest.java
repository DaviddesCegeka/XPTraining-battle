package be.cegeka.battle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SoldierTest {
    final String SOLDIER_NAME = "name";

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
    public void givenSoldier_whenSetSpearAsWeapon_thenSoldierHasSpear() {
        Soldier soldier = new Soldier(SOLDIER_NAME);
        soldier.equipWeapon(new Spear());

        assertThat(soldier.getWeapon()).isInstanceOf(Spear.class);
    }

    @Test
    public void givenSoldier_whenSetAxeAsWeapon_thenSoldierHasAxe() {
        Soldier soldier = new Soldier(SOLDIER_NAME);
        soldier.equipWeapon(new Axe());

        assertThat(soldier.getWeapon()).isInstanceOf(Axe.class);
    }

}
