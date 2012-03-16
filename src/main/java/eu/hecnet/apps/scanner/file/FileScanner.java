package eu.hecnet.apps.scanner.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import com.hp.hpl.jena.rdf.model.Resource;

import eu.hecnet.jena.vocabulary.FILE;

public class FileScanner {

	/**
	 * Populate properties of a file into the given resource
	 * 
	 * @param resource
	 * @param file
	 * @return type of file scanned
	 */
	public String scanFileForProperties(Resource resource, File file) {
		// String basename = file.getName();
		// int mid = basename.lastIndexOf(".");
		// String ext = "";
		// if (mid >= 0) {
		// ext = basename.substring(mid + 1, basename.length());
		// basename = basename.substring(0, mid);
		// }
		resource.addProperty(FILE.FILENAME, file.getName());
		// Get the creation date
		try {
			BasicFileAttributes attrs = Files.readAttributes(file.toPath(),
					BasicFileAttributes.class);
			resource.addProperty(FILE.CREATION_DATE, attrs.creationTime()
					.toString());
			resource.addProperty(FILE.SIZE, "" + attrs.size());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// resource.addProperty(FILE.BASENAME, basename);
		// resource.addProperty(FILE.EXT, ext);

		String fileType = null;
		// Determine file type via Java 7 NIO Files.probeContentType method
		try {
			fileType = Files.probeContentType(file.toPath());
			resource.addProperty(FILE.CONTENT_TYPE, fileType);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileType;
	}
}
