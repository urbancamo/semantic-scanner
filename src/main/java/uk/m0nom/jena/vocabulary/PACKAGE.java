package uk.m0nom.jena.vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

public class PACKAGE {
	protected static final String uri = "http://m0nom.uk/package-rdf/1.0#";

	/**
	 * returns the URI for this schema
	 * 
	 * @return the URI for this schema
	 */
	public static String getURI() {
		return uri;
	}

	private static final Model m = ModelFactory.createDefaultModel();

	public static final Property NAME = m.createProperty(uri + "NAME");
	public static final Property KEYWORDS = m.createProperty(uri + "KEYWORDS");
	public static final Property DESCRIPTION = m.createProperty(uri + "DESCRIPTION");
	public static final Property CONTAINS_SOURCE = m.createProperty(uri + "CONTAINS_SOURCE");
	public static final Property CONTAINS_BINARIES = m.createProperty(uri + "CONTAINS_BINARIES");
	public static final Property CONTAINS_OBJECTS = m.createProperty(uri + "CONTAINS_OBJECTS");
	public static final Property TARGET_ARCHITECTURES = m.createProperty(uri + "TARGET_ARCHITECTURES");
}
