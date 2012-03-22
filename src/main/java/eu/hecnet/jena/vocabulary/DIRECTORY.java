package eu.hecnet.jena.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

public class DIRECTORY {
	protected static final String uri = "http://hecnet.eu/directory-rdf/1.0#";

	/**
	 * @return the URI for this schema
	 */
	public static String getURI() {
		return uri;
	}

	private static Model m = ModelFactory.createDefaultModel();
	public static final Property FILE = m.createProperty(uri + "DIRECTORY");
	public static final Property FILES = m.createProperty(uri + "FILES");
	public static final Property PATHNAME = m.createProperty(uri + "PATHNAME");
	public static final Property BASENAME = m.createProperty(uri + "BASENAME");
	public static final Property CREATION_DATE = m.createProperty(uri + "CREATION_DATE");
}
