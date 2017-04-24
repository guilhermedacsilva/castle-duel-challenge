public class Player2 {

	int action = 0;
	
	public int play(
			int gold, 
			int[] units, 
			int[] enemies,
			int castle,
			int castleEnemy
			) {
		
		if (action == 0 && gold >= 20) {
			action = 1;
		}
		if (action == 1) {
			return 0;
		}
		return -1;
	}
	
}
