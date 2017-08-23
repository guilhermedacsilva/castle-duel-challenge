package br.game.castleduel.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileUtil {
	public static void cleanDir(final File folder) {
		for(File file: folder.listFiles()) {
			if (!file.isDirectory()) {
				file.delete();	
			}
		}
	}

	public static void write(String filename, String content) {
		try {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			content += "\n\n";
			Files.write(file.toPath(), content.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
