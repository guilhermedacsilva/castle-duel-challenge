package br.game.castleduel.util;

import java.io.File;

public class FileUtil {
	public static final File TMP = new File("tmp");

	public static void cleanDir(final File folder) {
		for(File file: folder.listFiles()) {
			if (!file.isDirectory()) {
				file.delete();	
			}
		}
	}
	
}
