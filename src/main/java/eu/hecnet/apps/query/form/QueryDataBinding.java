package eu.hecnet.apps.query.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.hecnet.apps.scanner.file.ModelFilenameFilter;
import eu.hecnet.file.detectors.CustomMimeType;
import eu.hecnet.file.detectors.openvms.VmsCustomMimeTypeLibrary;
import eu.hecnet.file.detectors.openvms.VmsFeature;
import eu.hecnet.jena.vms.LexicalRdf;

public class QueryDataBinding {
	public final String DONT_CARE = "Don't care";

	private VmsCustomMimeTypeLibrary mimeTypes;

	private String models[];
	private String fromYears[];
	private String toYears[];
	private QuerySize sizes[];
	private String features[];
	private CustomMimeType types[];
	private String lexicals[];

	public QueryDataBinding() {
		mimeTypes = new VmsCustomMimeTypeLibrary();

		// Determine list of model files to add
		File defaultDir = new File(".");

		String[] modelFiles = defaultDir.list(new ModelFilenameFilter());
		Arrays.sort(modelFiles);
		setModels(modelFiles);

		// Year options
		List<String> yearList = new ArrayList<String>();
		yearList.add(DONT_CARE);
		for (int i = 1979; i <= 2012; i++) {
			yearList.add("" + i);
		}
		setFromYears(yearList.toArray(new String[0]));
		setToYears(yearList.toArray(new String[0]));

		// Size options
		List<QuerySize> sizeList = new ArrayList<QuerySize>();
		sizeList.add(new QuerySize(DONT_CARE, 0));
		for (int i = 1; i <= 90; i += (i == 1 ? 9 : 10)) {
			sizeList.add(new QuerySize("" + i + "B", i));
		}
		for (int i = 100; i <= 900; i += 100) {
			sizeList.add(new QuerySize("" + i + "B", i));
		}
		for (int i = 1; i <= 90; i += (i == 1 ? 9 : 10)) {
			sizeList.add(new QuerySize("" + i + "K", i * 1000));
		}
		for (int i = 100; i <= 900; i += 100) {
			sizeList.add(new QuerySize("" + i + "K", i * 1000));
		}
		for (int i = 1; i <= 90; i += (i == 1 ? 9 : 10)) {
			sizeList.add(new QuerySize("" + i + "M", i * 1000000));
		}
		sizeList.add(new QuerySize(">100M", 1000001));
		setSizes(sizeList.toArray(new QuerySize[0]));

		// VMS Features
		List<String> featureList = new ArrayList<String>();
		featureList.add(DONT_CARE);
		featureList.add(VmsFeature.CALLING_STANDARD_LANGUAGE.getDescription());
		featureList.add(VmsFeature.SOURCE_FILE.getDescription());
		featureList.add(VmsFeature.MAKEFILE.getDescription());
		setFeatures(featureList.toArray(new String[0]));

		// Mime type options
		setTypes(mimeTypes.values().toArray(new CustomMimeType[0]));

		// Lexical options
		List<String> lexicalList = new ArrayList<String>();
		for (String lexical[] : LexicalRdf.LEXICALS) {
			lexicalList.add(lexical[0]);
		}
		Collections.sort(lexicalList);
		lexicalList.add(0, DONT_CARE);
		setLexicals(lexicalList.toArray(new String[0]));
	}

	public String[] getModels() {
		return models;
	}

	public void setModels(String[] models) {
		this.models = models;
	}

	public String[] getFromYears() {
		return fromYears;
	}

	public void setFromYears(String[] years) {
		this.fromYears = years;
	}

	public String[] getFeatures() {
		return features;
	}

	public void setFeatures(String[] features) {
		this.features = features;
	}

	public CustomMimeType[] getTypes() {
		return types;
	}

	public void setTypes(CustomMimeType[] types) {
		this.types = types;
	}

	public String[] getLexicals() {
		return lexicals;
	}

	public void setLexicals(String[] lexicals) {
		this.lexicals = lexicals;
	}

	public QuerySize[] getSizes() {
		return sizes;
	}

	public void setSizes(QuerySize sizes[]) {
		this.sizes = sizes;
	}

	public String[] getToYears() {
		return toYears;
	}

	public void setToYears(String toYears[]) {
		this.toYears = toYears;
	}
}
