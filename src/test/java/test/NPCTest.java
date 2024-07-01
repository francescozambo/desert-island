package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.NPC;
public class NPCTest {
	
	
	@Test
    public void testInteractState() {
        NPC npc = new NPC("npc1", 100, 10, "mapPiece1");

        // Verifica che inizialmente l'interazione con l'NPC sia false
        assertFalse(npc.getInteract());

        // Esegui l'interazione con l'NPC e verifica che l'interact sia true
        npc.setInteract();
        assertTrue(npc.getInteract());
    }

    @Test
    public void testHideState() {
        NPC npc = new NPC("npc1", 100, 10, "mapPiece1");

        // Verifica che inizialmente hide sia false
        assertFalse(npc.getHide());

        // Esegui il setHide e verifica che hide sia true
        npc.setHide();
        assertTrue(npc.getHide());

        // Esegui il setHide di nuovo e verifica che hide sia tornato a false
        npc.setHide();
        assertFalse(npc.getHide());
    }
}
