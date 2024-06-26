package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Character;

class CharacterTest {

	private Character character;

    @BeforeEach
    public void setUp() {
        character = new Character("testCharacter", 100, 10);
    }

    @Test
    public void testGiveHealth() {
        character.removeHealth(50); // Toglie 50 di vita
        character.giveHealth(30);	//Da 50 di vita
        assertEquals(80, character.getHealth());
        character.giveHealth(50); // Health should not exceed maxHealth
        assertEquals(100, character.getHealth());
    }

    @Test
    public void testRemoveHealthAndNotDie() {
        character.removeHealth(30);
        assertEquals(70, character.getHealth());
        assertTrue(character.isAlive());
    }
    @Test
    public void testRemoveHealthAndDie() {
        character.removeHealth(100);
        assertEquals(0, character.getHealth());
        assertFalse(character.isAlive());
    }

    @Test
    public void testIsAlive() {
        assertTrue(character.isAlive());
        character.removeHealth(100);
        assertFalse(character.isAlive());
    }

    @Test
    public void testGetIdCharacter() {
        assertEquals("testCharacter", character.getIdCharacter());
    }

    @Test
    public void testGetMaxHealth() {
        assertEquals(100, character.getMaxHealth());
    }

    @Test
    public void testGetHealth() {
        assertEquals(100, character.getHealth());
    }

    @Test
    public void testGetDamage() {
        assertEquals(10, character.getDamage());
    }

}
