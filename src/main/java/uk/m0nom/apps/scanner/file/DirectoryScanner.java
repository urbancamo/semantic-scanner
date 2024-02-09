package uk.m0nom.apps.scanner.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import com.hp.hpl.jena.rdf.model.Resource;

import uk.m0nom.jena.vocabulary.DIRECTORY;

public class DirectoryScanner {

	/**
	 * Populate properties of a directory into the given resource
	 * 
	 * @param resource
	 * @param directory
	 * @return type of file scanned
	 */
	public void scanFileForProperties(Resource resource, File directory) {
		String basename = directory.getName();
		resource.addProperty(DIRECTORY.BASENAME, basename);
		// Get the creation date
		try {
			BasicFileAttributes attrs = Files.readAttributes(directory.toPath(), BasicFileAttributes.class);
			resource.addProperty(DIRECTORY.CREATION_DATE, attrs.creationTime().toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resource.addProperty(DIRECTORY.PATHNAME, directory.getAbsolutePath());
	}

}
