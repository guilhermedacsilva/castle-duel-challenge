package br.game.castleduel.player;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.game.castleduel.util.FileUtil;
import br.game.castleduel.util.SourceCodeFileFilter;

public class PlayerFilesPreparator {
	private static final File FOLDER_PLAYERS = new File("players");
	private static final PlayerFilesPreparator SINGLETON = new PlayerFilesPreparator();
	private static final Map<String, String> TMP_NAME_TO_REAL_NAME = new HashMap<>(); 
	private static int uniqueId = 0;
	private File[] sourceCodes;
	private int playerIndex1;
	private int playerIndex2;
	private static final String[] TMP_NAMES = new String[2];

	public PlayerFilesPreparator() {
		sourceCodes = FOLDER_PLAYERS.listFiles(new SourceCodeFileFilter());
		playerIndex1 = 0;
		playerIndex2 = 0;
	}
	
	public static boolean prepare() {
		try {
			return SINGLETON.run();	
		} catch (IOException e) {
			e.printStackTrace();
			FileUtil.write("error.log", e.getMessage());
		}
		return false;
	}
	
	private boolean run() throws IOException {
		if (!choosePlayerIndex()) {
			return false;
		}
		FileUtil.cleanDir(PlayerCompiler.TEMP_PLAYERS);
		TMP_NAMES[0] = createTempJavaFile(sourceCodes[playerIndex1]);
		TMP_NAMES[1] = createTempJavaFile(sourceCodes[playerIndex2]);
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

	private String createTempJavaFile(File sourceCode) throws IOException {
		uniqueId++;
		final String newClassname = "Player" + uniqueId;
		final String newFilename = newClassname + ".java";
		final Path newFilePath = new File("tmp" + File.separator + newFilename).toPath(); 
		
		final byte[] byteContent = Files.readAllBytes(sourceCode.toPath());
		String content = new String(byteContent, StandardCharsets.UTF_8);
		content = content.replaceFirst("class.+\\{", "class " + newClassname + " {");
		Files.write(newFilePath, content.getBytes());
		
		TMP_NAME_TO_REAL_NAME.put(newFilename, sourceCode.getName());
		return newFilename;
	}
	
	public static Collection<String> getRealNames() {
		return TMP_NAME_TO_REAL_NAME.values();
	}

	public static String getPlayerRealName(String player) {
		return TMP_NAME_TO_REAL_NAME.get(player);
	}

	public static String getOtherPlayerRealName(String player) {
		String wonTmpName = TMP_NAMES[0].equals(player) ? TMP_NAMES[1] : TMP_NAMES[0];
		return getPlayerRealName(wonTmpName);
	}
	
}
