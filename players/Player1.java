public class Player1 {

	boolean buy = false;
	
	public int play(
			int gold, 
			int[] units, 
			int[] enemies,
			int castle,
			int castleEnemy
			) {
		
		if (gold >= 29) {
			buy = true;
		}
		if (buy) {
			return 0;
		}
		return -1;
	}
	
}
