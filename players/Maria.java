public class Maria {
	// ------ AÇÕES ---------------------
	private static final int WAIT		= -1; // NÃO COMPRA NENHUMA UNIDADE
	private static final int TYRION		= 0;
	private static final int MELISANDRE	= 1;
	private static final int HODOR		= 2;
	private static final int JON_SNOW	= 3;
	private static final int DAENERYS	= 4;
	private static final int CLEGANE	= 5;
	// ----------------------------------
	
	public int play(int gold,  // OURO
					int[] units, // UNIDADES ALIADAS.
					// units[TYRION] é a quantidade de TYRION aliados
					int[] enemies, // UNIDADES INIMIGAS
					// enemies[TYRION] é a quantidade de TYRION inimigos
					int castle, // VIDA DO CASTELO ALIADO
					int castleEnemy // VIDA DO CASTELO INIMIGO
					) {
		
		return TYRION; // retorna a AÇÃO
	}
	
	public String getName() {
		return "Maria";
	}
}

