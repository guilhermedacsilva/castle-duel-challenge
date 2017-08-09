package br.game.castleduel.player;

import java.io.File;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import br.game.castleduel.exception.CompilerException;
import br.game.castleduel.exception.PlayerException;
import br.game.castleduel.util.SourceCodeFileFilter;

public class PlayerCompiler {
	private static final File TEMP_PLAYERS = new File("tmp");
	
	public static String[] compileFiles() throws PlayerException {
		File[] sourceCodes = TEMP_PLAYERS.listFiles(new SourceCodeFileFilter());
		if (sourceCodes.length != 2) {
			throw new RuntimeException("Java code not found in tmp folder. Found: " + sourceCodes.length);
		}
		String[] filenames = new String[2];
		
		try {
			JavaCompiler compiler = getCompiler();
			filenames[0] = compileFile(compiler, sourceCodes, 0);
			filenames[1] = compileFile(compiler, sourceCodes, 1);
		} catch (CompilerException e) {
			System.out.println("Could not find the compiler!\nPlease, configure your machine.");
			System.exit(1);
		}
		return filenames;
	}
	
	protected static JavaCompiler getCompiler() throws CompilerException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new CompilerException();
		}
		return compiler;
	}

	
	protected static String compileFile(
			JavaCompiler compiler,
			File[] playerFiles,
			int player)
			throws PlayerException
	{
		File file = playerFiles[player];
		int result = compiler.run(null, null, null, file.getAbsolutePath());
		if (result != 0) {
			System.out.println("Could not compile the file: "+file.getAbsolutePath()+".");
			throw new PlayerException(player+1, file.getName());
		}
		return file.getName();
	}
}
