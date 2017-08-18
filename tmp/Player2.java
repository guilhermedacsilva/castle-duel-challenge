public class Player2 {
	private static final int WAIT     = -1; // NÃO COMPRA NENHUMA UNIDADE
	private static final int SKELETON = 0; // ESQUELETO
	private static final int ARCHER   = 1; // ARQUEIRO
	private static final int OGRE     = 2; // OGRO
	private static final int ZOMBIE   = 3; // ZUMBI
	private static final int MAGE     = 4; // MAGO
	private static final int CYCLOPS  = 5; // CICLOPE

	int action = 0;
	int qnt = 0;
	
	/**
	 * gold = seu dinheiro
	 * units[SKELETON] = quantidade de esqueletos que você tem na batalha
	 *   units[...] = quantidade de ... que você tem na batalha
	 * enemies[SKELETON] = quantidade de esqueletos que o inimigo tem
	 *   enemies[...] = quantidade de ... que o inimigo tem
	 * castle = vida do seu castelo
	 * castleEnemy = vida do castelo inimigo
	 * 
	 * Deve retornar o número da unidade que deseja comprar.
	 *   Retorne um valor inválido (-1) para não comprar.
	 */
	public int play(
			int gold, 
			int[] units, 
			int[] enemies,
			int castle,
			int castleEnemy
			) {
		
		if (action == 0 && gold >= 19) {
			action = 1;
		}
		if (action == 1 && qnt < 5 && gold >= 4) {
			qnt++;
			return ARCHER;
		}
		if (qnt >= 5 && gold >= 20) {
			action = 2;
		}
		if (action == 2) {
			return ZOMBIE;
		}
		return WAIT;
	}
	
	public String getName() {
		return "João";
	}
}
