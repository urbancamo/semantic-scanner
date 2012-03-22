package eu.hecnet.jena.vms;

import java.util.HashMap;
import java.util.Map;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Lexical functions callable from DCL. A static map is created containing
 * resources related to lexical functions
 * 
 * @author msw
 * 
 */
public class LexicalRdf {
	protected static final String uri = "http://hecnet.eu/lexical-rdf/1.0#";

	/** The URI for the help property of the lexical resource */
	public Property NAME;
	public Property HELP_URL;

	public static String LEXICALS[] = { "F$CONTEXT", "F$CSID", "F$CVSI",
			"F$CVTIME", "F$CVUI", "F$DEVICE", "F$DIRECTORY", "F$EDIT",
			"F$ELEMENT", "F$ENVIRONMENT", "F$EXTRACT", "F$FAO",
			"F$FILE_ATTRIBUTES", "F$GETDVI", "F$GETENV", "F$GETJPI",
			"F$GETQUI", "F$GETSYI", "F$IDENTIFIER", "F$INTEGER", "F$LENGTH",
			"F$LOCATE", "F$MESSAGE", "F$MODE", "F$PARSE", "F$PID",
			"F$PRIVILEGE", "F$PROCESS", "F$SEARCH", "F$SETPRV", "F$STRING",
			"F$TIME", "F$TRNLNM", "F$TYPE", "F$USER", "F$VERIFY" };

	public static String HELP_BASE_URL = "http://slave/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=";
	public static int HELP_CHUNK_START = 113;

	private Map<String, Resource> lexicalResources = new HashMap<String, Resource>();

	/**
	 * @return the URI for this schema
	 */
	public static String getUri() {
		return uri;
	}

	public LexicalRdf() {
	}

	public LexicalRdf(Model m) {
		setModel(m);
	}

	public void setModel(Model m) {
		NAME = m.createProperty(uri + "NAME");
		HELP_URL = m.createProperty(uri + "HELP_URL");
		addLexicalResources(m);
	}

	public String getUriForLexical(String lexicalName) {
		String lexicalUri = getUri() + lexicalName.toUpperCase();
		return lexicalUri;
	}

	public void addLexicalResources(Model m) {
		for (int i = 0; i < LEXICALS.length; i++) {
			String lexical = LEXICALS[i];
			Resource r = m.createResource(getUriForLexical(lexical));
			int chunk = HELP_CHUNK_START + i;
			r.addProperty(NAME, lexical.toUpperCase());
			r.addProperty(HELP_URL, HELP_BASE_URL + chunk);
			lexicalResources.put(lexical, r);
		}
	}

	public Resource getResource(String lexical) {
		return lexicalResources.get(lexical);
	}
}
