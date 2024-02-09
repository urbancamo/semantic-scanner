package uk.m0nom.jena.vms;

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

	public static String[] LEXICALS[] = {
			{ "F$CONTEXT", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=113" },
			{ "F$CSID", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=114" },
			{ "F$CVSI", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=115" },
			{ "F$CVTIME", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=116" },
			{ "F$CVUI", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=117" },
			{ "F$DEVICE", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=118" },
			{ "F$DIRECTORY", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=119" },
			{ "F$EDIT", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=120" },
			{ "F$ELEMENT", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=121" },
			{ "F$ENVIRONMENT", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=122" },
			{ "F$EXTRACT", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=123" },
			{ "F$FAO", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=124" },
			{ "F$FILE_ATTRIBUTES", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=126" },
			{ "F$GETDVI", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=128" },
			{ "F$GETENV", "http://zinser.no-ip.info/www/eng/vms/qaa/getenv.htmlx" },
			{ "F$GETJPI", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=132" },
			{ "F$GETQUI", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=134" },
			{ "F$GETSYI", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=137" },
			{ "F$IDENTIFIER", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=140" },
			{ "F$INTEGER", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=141" },
			{ "F$LENGTH", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=142" },
			{ "F$LOCATE", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=143" },
			{ "F$MESSAGE", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=144" },
			{ "F$MODE", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=146" },
			{ "F$PARSE", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=147" },
			{ "F$PID", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=148" },
			{ "F$PRIVILEGE", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=149" },
			{ "F$PROCESS", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=150" },
			{ "F$SEARCH", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=151" },
			{ "F$SETPRV", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=152" },
			{ "F$STRING", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=153" },
			{ "F$TIME", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=154" },
			{ "F$TRNLNM", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=155" },
			{ "F$TYPE", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=156" },
			{ "F$USER", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=158" },
			{ "F$VERIFY", "http://slave.hecnet.eu/HyperReader?file=dsa1:[decw$book.openvms]vy4yaaa6.decw$book&Chunk=159" } };

	private Map<String, Resource> lexicalResources = new HashMap<String, Resource>();

	public LexicalRdf() {

	}

	/**
	 * @return the URI for this schema
	 */
	public static String getUri() {
		return uri;
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
        return getUri() + lexicalName.toUpperCase();
	}

	public void addLexicalResources(Model m) {
        for (String[] strings : LEXICALS) {
            String lexical = strings[0];
            Resource r = m.createResource(getUriForLexical(lexical));
            String helpUrl = strings[1];
            r.addProperty(NAME, lexical.toUpperCase());
            r.addProperty(HELP_URL, helpUrl);
            lexicalResources.put(lexical, r);
        }
	}

	public Resource getResource(String lexical) {
		return lexicalResources.get(lexical);
	}

	public Map<String, Resource> getLexicalResources() {
		return lexicalResources;
	}

	public void setLexicalResources(Map<String, Resource> lexicalResources) {
		this.lexicalResources = lexicalResources;
	}

}
