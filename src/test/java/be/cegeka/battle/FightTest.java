package be.cegeka.battle;

import org.junit.jupiter.api.Test;

import static be.cegeka.battle.TestConstants.NAME_JOHN_DOE;
import static be.cegeka.battle.TestConstants.NAME_RON_DOE;
import static be.cegeka.battle.Weapon.*;
import static org.assertj.core.api.Assertions.assertThat;

class FightTest {

    @Test
    void attack_whenAttackerHasStrongerWeapon_expectAttackerWins() {
        Soldier attacker = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier defender = new Soldier(NAME_RON_DOE, BARE_FIST);
        Fight fight = new Fight(attacker, defender);

        Soldier winner = fight.getWinner();

        assertThat(winner).isEqualTo(attacker);
    }

    @Test
    void attack_whenDefenderHasStrongerWeapon_expectDefenderWins() {
        Soldier attacker = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier defender = new Soldier(NAME_RON_DOE, AXE);
        Fight fight = new Fight(attacker, defender);

        Soldier winner = fight.getWinner();

        assertThat(winner).isEqualTo(defender);
    }

    @Test
    void attack_whenWeaponsAreEquallyStrong_expectAttackerWins() {
        Soldier attacker = new Soldier(NAME_JOHN_DOE, SWORD);
        Soldier defender = new Soldier(NAME_RON_DOE, SWORD);
        Fight fight = new Fight(attacker, defender);

        Soldier winner = fight.getWinner();

        assertThat(winner).isEqualTo(attacker);
    }
}
