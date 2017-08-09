package br.game.castleduel.util;

import java.io.File;
import java.io.FilenameFilter;

public class SourceCodeFileFilter implements FilenameFilter {
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".java");
	}
}
