package test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Mob;
import main.Player;
class MobTest {
	
	private Player player;
	private Mob mob;
	
	@BeforeEach
	void setUp() throws Exception {
		player = new Player("Player", 100, 10);
        mob = new Mob("Mob", 50, 10);
	}
		
	@Test
	public void testFlee() {
		int initialHealth = player.getHealth();
		mob.flee(player);
		if(initialHealth+mob.getfleeMalus()>=player.getMaxHealth())
			 assertEquals(player.getMaxHealth(), player.getHealth());
		else
			 assertEquals(initialHealth+mob.getfleeMalus(), player.getHealth());
	}
	 @Test
	    public void testAttack() {
	        int initialHealth = player.getHealth();
	        mob.attack(player);
	        assertEquals(initialHealth - mob.getDamage(), player.getHealth());
	    }
	 @Test
	    public void testSmallAttack() {
	        int initialHealth = player.getHealth();
	        mob.smallAttack(player);
	        assertEquals(initialHealth - mob.getSmallAttack(), player.getHealth());
	    }

	    @Test
	    public void testCure() {
	        int initialHealth = mob.getHealth();
	        mob.cure();
	        if(initialHealth + mob.getActioncure()>=mob.getMaxHealth())
	        	assertEquals(mob.getMaxHealth(), mob.getHealth());
	        else
	        	assertEquals(initialHealth+mob.getActioncure(), mob.getHealth());
	    }

}
