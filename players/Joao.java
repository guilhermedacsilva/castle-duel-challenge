public class Joao {
	private static final int WAIT		= -1; // NÃO COMPRA NENHUMA UNIDADE
	private static final int TYRION		= 0;
	private static final int MELISANDRE	= 1;
	private static final int HODOR		= 2;
	private static final int JON_SNOW	= 3;
	private static final int DAENERYS	= 4;
	private static final int MOUNTAIN	= 5;

	int action = 0;
	int qnt = 0;
	
	/**
	 * gold = seu dinheiro
	 * units[TYRION] = quantidade de TYRION que você tem na batalha
	 * enemies[TYRION] = quantidade de TYRION que o inimigo tem
	 * castle = vida do seu castelo
	 * castleEnemy = vida do castelo inimigo
	 * 
	 * Deve retornar o número da unidade que deseja comprar [0, 5].
	 * Retorne um valor inválido (WAIT) para não comprar.
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
			return MELISANDRE;
		}
		if (qnt >= 5 && gold >= 20) {
			action = 2;
		}
		if (action == 2) {
			return JON_SNOW;
		}
		return WAIT;
	}
	
	public String getName() {
		return "João";
	}
}
