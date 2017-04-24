package br.game.castleduel.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class UniqueIdTest {

	@Test
	public void testEquals() {
		Concrete concrete = new Concrete(3);
		List<Concrete> list = new ArrayList<>();
		list.add(concrete);
		list.add(new Concrete(0));
		list.add(new Concrete(0));
		list.add(new Concrete(0));
		list.add(new Concrete(0));
		Collections.shuffle(list);
		
		assertTrue(list.remove(concrete));
		
		for (Concrete c : list) {
			if (c.n != 0) {
				fail("Removed the wrong item");
			}
		}
	}
	
	public static class Concrete extends UniqueId {
		final int n;
		public Concrete(int n) {
			this.n = n;
		}
	}

}
