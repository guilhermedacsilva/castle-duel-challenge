package br.game.castleduel.player;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import br.game.castleduel.util.FileUtil;
import br.game.castleduel.util.SourceCodeFileFilter;

public class PlayerFilesPreparator {
	private static final File FOLDER_PLAYERS = new File("players");
	private static PlayerFilesPreparator SINGLETON;
	private static int uniqueId = 0;
	private File[] sourceCodes;
	private int playerIndex1;
	private int playerIndex2;

	public PlayerFilesPreparator() {
		sourceCodes = FOLDER_PLAYERS.listFiles(new SourceCodeFileFilter());
		playerIndex1 = 0;
		playerIndex2 = 0;
	}
	
	public static boolean prepare() {
		if (SINGLETON == null) {
			SINGLETON = new PlayerFilesPreparator();
		}
		try {
			return SINGLETON.run();	
		} catch (IOException e) {
			System.out.println("Java source could not be read.");
			System.exit(1);
		}
		return false;
	}
	
	private boolean run() throws IOException {
		if (!choosePlayerIndex()) {
			return false;
		}
		FileUtil.cleanDir(new File("tmp"));
		createTempJavaFile(sourceCodes[playerIndex1]);
		createTempJavaFile(sourceCodes[playerIndex2]);
		return true;
	}
	
	private boolean choosePlayerIndex() {
		playerIndex2++;
		if (playerIndex2 == sourceCodes.length) {
			playerIndex1++;
			playerIndex2 = playerIndex1 + 1;
			if (playerIndex2 == sourceCodes.length) {
				return false;
			}
		}
		return true;
	}

	private void createTempJavaFile(File sourceCode) throws IOException {
		uniqueId++;
		final String newClassname = "Player" + uniqueId;
		final String newFilename = newClassname + ".java";
		final Path newFilePath = new File("tmp" + File.separator + newFilename).toPath(); 
		
		final byte[] byteContent = Files.readAllBytes(sourceCode.toPath());
		String content = new String(byteContent, StandardCharsets.UTF_8);
		content = content.replaceFirst("class.+\\{", "class " + newClassname + " {");
		Files.write(newFilePath, content.getBytes());
	}
	
}
