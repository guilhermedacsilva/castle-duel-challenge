public class Player2 {

	int action = 0;
	
	public int play(
			int gold, 
			int[] units, 
			int[] enemies,
			int castle,
			int castleEnemy
			) {
		
		if (action == 0 && gold >= 30) {
			action = 1;
		}
		if (action == 1) {
			action = 2;
			return 4;
		}
		if (action == 2) {
			return 4;
		}
		return -1;
	}
	
}
