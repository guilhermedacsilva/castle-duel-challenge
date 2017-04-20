package br.game.castleduel.util;

import java.util.Arrays;

public class Matrix {
	public static void reset(int[][] matrix) {
		for (int[] row : matrix) {
			Arrays.fill(row, 0);
		}
	}
}
