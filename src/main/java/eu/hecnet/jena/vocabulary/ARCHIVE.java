package eu.hecnet.jena.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

public class ARCHIVE {
	protected static final String uri = "http://hecnet.eu/archive-rdf/1.0#";

	/**
	 * @return the URI for this schema
	 */
	public static String getURI() {
		return uri;
	}

	private static Model m = ModelFactory.createDefaultModel();

	public static final Property NAME = m.createProperty(uri + "NAME");
	public static final Property DESCRIPTION = m.createProperty(uri + "DESCRIPTION");
}
