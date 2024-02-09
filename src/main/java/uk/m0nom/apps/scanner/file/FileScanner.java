package uk.m0nom.apps.scanner.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

import uk.m0nom.jena.vms.LexicalRdf;
import uk.m0nom.jena.vocabulary.FILE;

public class FileScanner {

	public final static String OPENVMS_TEXTFILE_CHARSET = "iso-8859-1";

	protected static LinkOption[] LINK_OPTIONS = {};

	private LexicalRdf lexicalRdf;

	/**
	 * Populate properties of a file into the given resource
	 * 
	 * @param resource
	 * @param file
	 * @return type of file scanned
	 */
	public String scanFileForProperties(Resource resource, File file) {
		String basename = file.getName();
		int mid = basename.lastIndexOf(".");
		String ext = "";
		if (mid >= 0) {
			ext = basename.substring(mid + 1, basename.length());
			basename = basename.substring(0, mid);
		}
		resource.addProperty(FILE.FILENAME, file.getName());
		// Get the creation date
		try {
			BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			resource.addProperty(FILE.CREATION_DATE, attrs.creationTime().toString());
			resource.addProperty(FILE.SIZE, "" + attrs.size());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resource.addProperty(FILE.BASENAME, basename);
		resource.addProperty(FILE.EXT, ext);
		resource.addProperty(FILE.PATHNAME, file.getAbsolutePath());

		String fileType = null;
		// Determine file type via Java 7 NIO Files.probeContentType method
		try {
			fileType = Files.probeContentType(file.toPath());
			resource.addProperty(FILE.CONTENT_TYPE, fileType);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Special processing based on fileType
		if ("vms/dcl-script".equals(fileType)) {
			searchDclFileForLexicals(resource, file.toPath());
		}

		return fileType;
	}

	private void searchDclFileForLexicals(Resource resource, Path path) {
		if (Files.isRegularFile(path, LINK_OPTIONS)) {
			BufferedReader br = null;
			try {
				br = Files.newBufferedReader(path, Charset.forName(OPENVMS_TEXTFILE_CHARSET));
				while (br.ready()) {
					String line = br.readLine();
					if (line != null) {
						if (line.toUpperCase().contains("F$")) {
							for (String[] lexical : LexicalRdf.LEXICALS) {
								String lexicalName = lexical[0];
								if (line.contains(lexicalName)) {
									// Add a link to the lexical
									Model m = resource.getModel();
									Resource lexicalResource = lexicalRdf.getResource(lexicalName);
									m.add(resource, FILE.LEXICAL_USE, lexicalResource.getURI());
								}
							}
						}
					}
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void setLexicalRdf(LexicalRdf lexicalRdf) {
		this.lexicalRdf = lexicalRdf;
	}

	public LexicalRdf getLexicalRdf() {
		return lexicalRdf;
	}

}
