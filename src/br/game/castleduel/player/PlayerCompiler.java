package br.game.castleduel.player;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import br.game.castleduel.exception.CompilerException;
import br.game.castleduel.exception.PlayerException;

public class PlayerCompiler {
	protected static final String PATH = "players/Player%d.java";
	
	public static void compileFiles() throws PlayerException {
		try {
			JavaCompiler compiler = getCompiler();
			compileFile(compiler, 1);
			compileFile(compiler, 2);
		} catch (CompilerException e) {
			System.out.println("Could not find the compiler!\nPlease, configure your machine.");
			System.exit(1);
		}
	}
	
	protected static JavaCompiler getCompiler() throws CompilerException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new CompilerException();
		}
		return compiler;
	}

	
	protected static void compileFile(
			JavaCompiler compiler,
			int player)
			throws PlayerException
	{
		String path = String.format(PATH, player);
		int result = compiler.run(null, null, null, path);
		if (result != 0) {
			System.out.println("Could not compile the file: "+path+".");
			throw new PlayerException(player);
		}
	}
}
