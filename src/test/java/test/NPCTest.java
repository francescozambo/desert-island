package test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.NPC;
public class NPCTest {
	private NPC npc;
	@BeforeEach
	public void setUp() {
		npc = new NPC("npc1", 100, 10, "mapPiece1");
	}
	
	
	@Test
    public void testInteractState() {

        assertFalse(npc.getInteract());	
        npc.setInteract();
        assertTrue(npc.getInteract());
    }

    @Test
    public void testHideState() {
        assertFalse(npc.getHide());
        npc.setHide();
        assertTrue(npc.getHide());
        npc.setHide();
        assertFalse(npc.getHide());
    }
}
