public class Player1 {

	boolean buy = false;
	
	public int play(
			int gold, 
			int[] units, 
			int[] enemies,
			int castle,
			int castleEnemy
			) {
		
		if (gold >= 19) {
			buy = true;
		}
		if (buy) {
			return 1;
		}
		return -1;
	}
	
}
