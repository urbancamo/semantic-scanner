package eu.hecnet.apps.scanner.file;

import java.io.File;
import java.io.FilenameFilter;

public class ModelFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return name.startsWith("model-");
	}

}
