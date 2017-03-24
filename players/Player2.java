public class Player2 {

	boolean buy = false;
	
	public int play(
			int gold, 
			int[] units, 
			int[] enemies,
			int castle,
			int castleEnemy
			) {
		
		if (gold >= 25) {
			buy = true;
		}
		if (buy) {
			return 3;
		}
		return -1;
	}
	
}
