package uk.m0nom.jena.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

public class FILE {
	protected static final String uri = "http://hecnet.eu/file-rdf/1.0#";

	/**
	 * returns the URI for this schema
	 * 
	 * @return the URI for this schema
	 */
	public static String getURI() {
		return uri;
	}

	private static final Model m = ModelFactory.createDefaultModel();

	public static final Property FILE = m.createProperty(uri + "FILE");
	public static final Property PATHNAME = m.createProperty(uri + "PATHNAME");
	public static final Property FILENAME = m.createProperty(uri + "FILENAME");
	public static final Property BASENAME = m.createProperty(uri + "BASENAME");
	public static final Property EXT = m.createProperty(uri + "EXT");
	public static final Property CREATION_DATE = m.createProperty(uri
			+ "CREATION_DATE");
	public static final Property SIZE = m.createProperty(uri + "SIZE");
	public static final Property CONTENT_TYPE = m.createProperty(uri
			+ "CONTENT_TYPE");
	public static final Property LEXICAL_USE = m.createProperty(uri
			+ "LEXICAL_USE");

}
