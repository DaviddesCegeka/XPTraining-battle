package be.cegeka.battle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WeaponTest {

    @ParameterizedTest
    @MethodSource("weaponAndStrenghts")
    void getWeaponStrength(Weapon weapon, int expectedStrength) {
        assertThat(weapon.getWeaponStrength()).isEqualTo(expectedStrength);
    }

    private static Stream<Arguments> weaponAndStrenghts() {
        return Stream.of(
                Arguments.of(Weapon.BARE_FIST, 1),
                Arguments.of(Weapon.AXE, 3),
                Arguments.of(Weapon.SWORD, 2),
                Arguments.of(Weapon.SPEAR, 2));
    }
}
